package com.system.edu.controller;

import com.system.edu.models.dao.Plan;
import com.system.edu.web.dao.PlansDao;
import com.system.edu.web.dao.SubjectsDao;
import com.system.edu.web.dao.TeachersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private PlansDao plansDao;

    @Autowired
    private TeachersDao teachersDao;


    @Autowired
    private SubjectsDao subjectsDao;


    @RequestMapping(value = {"/plans"}, method = RequestMethod.GET)
    public String listOfPlans(ModelMap model, @ModelAttribute Plan planModel) {
        logger.info("IN: plans/list-GET");

        List<Plan> plansList = plansDao.getAllPlans();
        model.addAttribute("plansList", plansList);
        model.addAttribute("teacherList", teachersDao.getAllTeachers());
        model.addAttribute("subjectsList", subjectsDao.getAllSubjects());
        return "curriculum/plans";
    }


    @RequestMapping(value = "/plans/add", method = RequestMethod.POST)
    public String addingPlans(@Valid @ModelAttribute Plan plansModel,
                              BindingResult bindingResult, ModelMap model) {

        logger.info("IN: Plans/add-POST");

        if (bindingResult.hasErrors() || plansDao.isPlanExists(plansModel)) {
            return listOfPlans(model, plansModel);
        } else {
            plansDao.addPlan(plansModel);
            return "redirect:/plans";
        }
    }

    @RequestMapping(value = "/plans/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Plans/delete-GET:  ID to query = " + id);

        if (!model.containsAttribute("plans")) {
            logger.info("Delete Plan object to model");
            plansDao.deletePlan(id);
        }

        return "redirect:/plans";
    }

}