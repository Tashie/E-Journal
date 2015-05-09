package com.system.edu.controller;

import com.system.edu.models.dao.*;
import com.system.edu.web.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class PlanClassesController {
    static Logger logger = LoggerFactory.getLogger(PlansController.class);

    @Autowired
    private PlansService plansService;

    @Autowired
    private PlanClassesService planClassesService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private TeachersService teachersService;

    @Autowired
    private SubjectsService subjectsService;

    @RequestMapping(value = {"/planclasses"}, method = RequestMethod.GET)
    public String listOfPlanClasses(ModelMap model, @ModelAttribute PlanClass classesModel) {
        List<Subject> subjects = plansService.getSubjects();
        List<String> years = plansService.getYears();
        List<Teacher> teachers = plansService.getTeachers();

        List<PlanClass> classes = planClassesService.getClasses(String.valueOf(teachers.get(0).getId()), String.valueOf(subjects.get(0).getId()), String.valueOf(years.get(0)));

        model.addAttribute("subjectsList", subjects);
        model.addAttribute("yearsList", years);
        model.addAttribute("teachersList", teachers);
        model.addAttribute("classesList", classes);
        model.addAttribute("selectedTeacherId", String.valueOf(teachers.get(0).getId()));
        model.addAttribute("selectedSubjectId", String.valueOf(subjects.get(0).getId()));
        model.addAttribute("selectedYear", String.valueOf(years.get(0)));

        return "curriculum/classes";
    }

    @RequestMapping(value = {"/planclasses/filter"}, method = RequestMethod.POST)
    public String filterPlanClasses(@RequestParam("teacherId") String teacherId,
                                    @RequestParam("subjectId") String subjectId,
                                    @RequestParam("years") String year,
                                    ModelMap model) {

        List<Subject> subjects = plansService.getSubjects();
        List<String> years = plansService.getYears();
        List<Teacher> teachers = plansService.getTeachers();

        List<PlanClass> classes = planClassesService.getClasses(teacherId, subjectId, year);

        model.addAttribute("subjectsList", subjects);
        model.addAttribute("yearsList", years);
        model.addAttribute("teachersList", teachers);
        model.addAttribute("classesList", classes);
        model.addAttribute("selectedTeacherId", teacherId);
        model.addAttribute("selectedSubjectId", subjectId);
        model.addAttribute("selectedYear", year);

        return "curriculum/classes";
    }

    @RequestMapping(value = {"/planclasses/add/{teacherId}/{subjectId}/{year}"}, method = RequestMethod.GET)
    public String addPlanClassesForm(@PathVariable int teacherId,
                                     @PathVariable int subjectId,
                                     @PathVariable String year,
                                     PlanClass planClasses,
                                     ModelMap model) {
        List<Classes> classes = classesService.getClasses();
        model.addAttribute("classes", classes);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("year", year);
        model.addAttribute("teacherName", teachersService.getTeacherName(teacherId));
        model.addAttribute("subjectName", subjectsService.getSubjectName(subjectId));

        return "curriculum/class_form";
    }

    @RequestMapping(value = "/planclasses/add", method = RequestMethod.POST)
    public String addCycle(@RequestParam("teacherId") int teacherId,
                           @RequestParam("subjectId") int subjectId,
                           @RequestParam("year") int year,
                           @Valid @ModelAttribute PlanClass planClass,
                           BindingResult result, ModelMap model) {

        logger.info("IN: planclasses/add-POST");

        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("year", year);
        model.addAttribute("teacherName", teachersService.getTeacherName(teacherId));
        model.addAttribute("subjectName", subjectsService.getSubjectName(subjectId));

        Plan plans = plansService.getPlans(teacherId, subjectId, year);

        if (plans != null) {
            if (result.hasErrors() || plansService.isPlanExists(plans)) {
                logger.info("Subject-add error: " + result.toString());

                List<Classes> classes = classesService.getClasses();
                model.addAttribute("classes", classes);
                model.addAttribute("error", "Plan already exists");

                return "curriculum/class_form";
            } else {
                planClass.setPlansByPlan(plans);
                if (planClassesService.addPlanClass(planClass)) {
                    return "redirect:/planclasses";
                } else {
                    model.addAttribute("error", "Plan addition error");
                    return "curriculum/class_form";
                }
            }
        } else {
            model.addAttribute("error", "There is no such a plan");
            return "curriculum/class_form";
        }
    }
}