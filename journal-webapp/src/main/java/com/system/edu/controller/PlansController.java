package com.system.edu.controller;

import com.system.edu.models.ui.Plans;
import com.system.edu.web.service.PlansService;
import com.system.edu.web.service.SubjectsService;
import com.system.edu.web.service.TeachersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * User: nata
 * Date: 27.06.14
 */

@Controller
public class PlansController {
    static Logger logger = LoggerFactory.getLogger(PlansController.class);

    @Autowired
    private PlansService plansService;

    @Autowired
    private TeachersService teachersService;


    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/plans"}, method = RequestMethod.GET)
    public String listOfPlans(Model model, @ModelAttribute Plans planModel) {
        logger.info("IN: plans/list-GET");

        List<Plans> plansList = plansService.getPlans();
        model.addAttribute("plansList", plansList);
        model.addAttribute("teacherList", teachersService.getTeachers());
        model.addAttribute("subjectsList", subjectsService.getSubjects());
        return "directories/plans";
    }


    @RequestMapping(value = "/plans/add", method = RequestMethod.POST)
    public String addingPlans(Model model, @Valid @ModelAttribute Plans plansModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        logger.info("IN: Plans/add-POST");

        if (bindingResult.hasErrors() || plansService.isPlanExists(plansModel)) {
            return listOfPlans(model, plansModel);
        } else {
            plansService.addPlan(plansModel);
            return "redirect:/plans";
        }
    }

    @RequestMapping(value = "/plans/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Plans/delete-GET:  ID to query = " + id);

        if (!model.containsAttribute("plans")) {
            logger.info("Delete Plan object to model");
            plansService.delete(id);
        }

        return "redirect:/plans";
    }


}