package com.system.edu.controller;

import com.system.edu.models.dao.Classes;
import com.system.edu.models.dao.Teacher;
import com.system.edu.web.dao.ClassesDao;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ClassesController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ClassesDao classesDao;

    @Autowired
    TeachersDao teachersDao;

    @RequestMapping(value = {"/classes"}, method = RequestMethod.GET)
    public String listOfClasses(Model model) {
        logger.info("IN: classes/list-GET");

        List<Classes> classes = new ArrayList();
        classes.addAll(classesDao.getAllClasses());
        model.addAttribute("classes", classes);

        return "directories/classes";
    }

    @RequestMapping(value = {"/classes/add"}, method = RequestMethod.GET)
    public String addSubjectForm(Classes classes, ModelMap model) {
        logger.info("IN: classes/add-GET");

        if (!model.containsAttribute("teachers")) {
            logger.info("Adding teachers object to model");

            List<Teacher> teachers = teachersDao.getAllTeachers();
            model.addAttribute("teachers", teachers);
        }

        return "directories/class";
    }

    @RequestMapping(value = "/classes/add", method = RequestMethod.POST)
    public String addCycle(@Valid @ModelAttribute Classes clazz,
                           BindingResult result, ModelMap model) {

        logger.info("IN: classes/add-POST");

        if (result.hasErrors() || classesDao.classExists(clazz.getName())) {
            logger.info("Class-add error: " + result.toString());

            List<Teacher> teachers = teachersDao.getAllTeachers();
            model.addAttribute("teachers", teachers);

            return "directories/class";
        } else {
            classesDao.addClass(clazz);
            return "redirect:/classes";
        }
    }
}