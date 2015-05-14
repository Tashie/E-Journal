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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/classes")
public class ClassesController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ClassesDao classesDao;

    @Autowired
    TeachersDao teachersDao;

    @RequestMapping(method = RequestMethod.GET)
    public String listOfClasses(Model model) {
        logger.info("IN: classes/list-GET");

        List<Classes> classes = new ArrayList();
        classes.addAll(classesDao.getAllClasses());
        model.addAttribute("classes", classes);

        return "directories/classes";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addClassesForm(Classes classes, ModelMap model) {
        logger.info("IN: classes/add-GET");

        if (!model.containsAttribute("teachers")) {
            logger.info("Adding class object to model");

            List<Teacher> teachers = teachersDao.getAllTeachers();
            model.addAttribute("teachers", teachers);
        }

        return "directories/class";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editClass(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: classes/edit-GET");

        if (!model.containsAttribute("teachers")) {
            logger.info("Adding class object to model");

            List<Teacher> teachers = teachersDao.getAllTeachers();
            model.addAttribute("teachers", teachers);

            model.addAttribute("classes", classesDao.getClass(id));
        }

        return "directories/class";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCycle(@Valid @ModelAttribute Classes clazz,
                           BindingResult result, ModelMap model) {

        logger.info("IN: classes/add-POST");

        if (result.hasErrors() || (clazz.getId() == 0 && classesDao.classExists(clazz.getName()))) {
            logger.info("Class-add error: " + result.toString());

            List<Teacher> teachers = teachersDao.getAllTeachers();
            model.addAttribute("teachers", teachers);
            result.rejectValue("name", "123", "Teacher with the same name already exists. Check your data again");
            return "directories/class";
        } else {
            classesDao.addClass(clazz);
            return "redirect:/classes";
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteClass(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Classes/delete-GET:  ID to query = " + id);
        classesDao.deleteClass(id);
        return "redirect:/classes";
    }
}