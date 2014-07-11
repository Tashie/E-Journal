package com.system.edu.controller;

import com.system.edu.models.dao.PlanClassesEntity;
import com.system.edu.models.ui.PlanClasses;
import com.system.edu.models.ui.Subjects;
import com.system.edu.models.ui.Teachers;
import com.system.edu.web.service.PlanClassesService;
import com.system.edu.web.service.PlansService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by sph on 10.07.2014.
 */

@Controller
public class PlanClassesController {
    static Logger logger = LoggerFactory.getLogger(PlansController.class);

    @Autowired
    private PlansService plansService;

    @Autowired
    private PlanClassesService planClassesService;

    @RequestMapping(value = {"/planclasses"}, method = RequestMethod.GET)
    public String listOfPlanClasses(ModelMap model, @ModelAttribute PlanClassesEntity classesModel) {
        List<Subjects> subjects = plansService.getSubjects();
        List<String> years = plansService.getYears();
        List<Teachers> teachers = plansService.getTeachers();

        List<PlanClasses> classes = planClassesService.getClasses(String.valueOf(teachers.get(0).getId()), String.valueOf(subjects.get(0).getId()), String.valueOf(years.get(0)));

        model.addAttribute("subjectsList", subjects);
        model.addAttribute("yearsList", years);
        model.addAttribute("teachersList", teachers);
        model.addAttribute("classesList", classes);

        return "curriculum/classes";
    }

    @RequestMapping(value = {"/planclasses/filter"}, method = RequestMethod.POST)
    public String listOfPlanClasses(@RequestParam("teacherId") String teacherId,
                                    @RequestParam("subjectId") String subjectId,
                                    @RequestParam("years") String year,
                                    ModelMap model) {

        List<Subjects> subjects = plansService.getSubjects();
        List<String> years = plansService.getYears();
        List<Teachers> teachers = plansService.getTeachers();

        List<PlanClasses> classes = planClassesService.getClasses(teacherId, subjectId, year);

        model.addAttribute("subjectsList", subjects);
        model.addAttribute("yearsList", years);
        model.addAttribute("teachersList", teachers);
        model.addAttribute("classesList", classes);
        model.addAttribute("selectedTeacherId", teacherId);
        model.addAttribute("selectedSubjectId", subjectId);
        model.addAttribute("selectedYear", year);

        return "curriculum/classes";
    }
}