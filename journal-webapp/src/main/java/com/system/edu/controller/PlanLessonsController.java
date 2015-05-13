package com.system.edu.controller;

import com.system.edu.models.dao.PlanLessonsEntity;
import com.system.edu.models.ui.PlanLessons;
import com.system.edu.web.dao.PlanLessonsDao;
import com.system.edu.web.service.PlanLessonsService;
import com.system.edu.web.service.PlansService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PlanLessonsController {
    static Logger logger = LoggerFactory.getLogger(PlansController.class);

    @Autowired
    PlanLessonsService planLessonsService;

    @RequestMapping(value = {"/planlessons"}, method = RequestMethod.GET)
    public String listOfPlanLessons(ModelMap model,
                                    @RequestParam("planId") int planId,
                                    @ModelAttribute PlanLessons planLessonModel) {

        List<PlanLessons> lessons = planLessonsService.getLessons(planId);

        model.addAttribute("lessons", lessons);
        model.addAttribute("planId", planId);
        return "curriculum/lessons";
    }

    @RequestMapping(value = "/planlessons/add", method = RequestMethod.POST)
    public String addingPlans(@Valid @ModelAttribute PlanLessons planLessonsModel,
                              @RequestParam("planId") int planId,
                              BindingResult bindingResult, ModelMap model) {

        logger.info("IN: Plans/add-POST");

        if (bindingResult.hasErrors()) {
            return listOfPlanLessons(model, planId, planLessonsModel);
        } else {
            planLessonsService.addLesson(planId, planLessonsModel);
            return "redirect:/planlessons?planId=" + planId;
        }
    }
}