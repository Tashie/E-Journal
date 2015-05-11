package com.system.edu.controller;

import com.system.edu.models.dao.*;
import com.system.edu.web.dao.*;
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
    private PlansDao plansDao;

    @Autowired
    private PlanClassesDao planClassesDao;

    @Autowired
    private ClassesDao classesDao;

    @Autowired
    private TeachersDao teachersDao;

    @Autowired
    private SubjectsDao subjectsDao;

    @RequestMapping(value = {"/planclasses"}, method = RequestMethod.GET)
    public String listOfPlanClasses(ModelMap model, @ModelAttribute PlanClass classesModel) {
        List<Subject> subjects = plansDao.getSubjects();
        List<String> years = plansDao.getYears();
        List<Teacher> teachers = plansDao.getTeachers();

        List<PlanClass> classes = planClassesDao.getClasses(String.valueOf(teachers.get(0).getId()), String.valueOf(subjects.get(0).getId()), String.valueOf(years.get(0)));

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

        List<Subject> subjects = plansDao.getSubjects();
        List<String> years = plansDao.getYears();
        List<Teacher> teachers = plansDao.getTeachers();

        List<PlanClass> classes = planClassesDao.getClasses(teacherId, subjectId, year);

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
        List<Classes> classes = classesDao.getAllClasses();
        model.addAttribute("classes", classes);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("year", year);
        model.addAttribute("teacherName", teachersDao.getTeacherName(teacherId));
        model.addAttribute("subjectName", subjectsDao.getSubjectName(subjectId));

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
        model.addAttribute("teacherName", teachersDao.getTeacherName(teacherId));
        model.addAttribute("subjectName", subjectsDao.getSubjectName(subjectId));

        Plan plans = plansDao.getPlans(teacherId, subjectId, year);

        if (plans != null) {
            if (result.hasErrors() || plansDao.isPlanExists(plans)) {
                logger.info("Subject-add error: " + result.toString());

                List<Classes> classes = classesDao.getAllClasses();
                model.addAttribute("classes", classes);
                model.addAttribute("error", "Plan already exists");

                return "curriculum/class_form";
            } else {
                planClass.setPlansByPlan(plans);
                if (planClassesDao.addPlanClass(planClass)) {
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