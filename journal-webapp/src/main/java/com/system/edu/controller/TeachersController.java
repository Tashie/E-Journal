package com.system.edu.controller;

import com.system.edu.models.ui.Teachers;
import com.system.edu.web.service.TeachersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Дмитрий on 11.07.14.
 */

@Controller
public class TeachersController {
    static Logger logger = LoggerFactory.getLogger(TeachersController.class);


@Autowired
private TeachersService teachersService;

    @RequestMapping(value = {"/teachers"}, method = RequestMethod.GET)
    public String listOfTeachers(Model model, @ModelAttribute Teachers teachersModel) {
        logger.info("IN: teachers/list-GET");

        List<Teachers> teachersList = teachersService.getTeachers();
        model.addAttribute("teachersList", teachersList);
        return "directories/teachers";
}
/*
    @RequestMapping(value = "/teachers/add", method = RequestMethod.POST)
    public String addingTeachers(Model model, @Valid @ModelAttribute Teachers teachersModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        logger.info("IN: Teachers/add-POST");

        if (bindingResult.hasErrors() || !teachersService.checkIsUniqueTeacherName(teachersModel.getLastname())) {
            logger.info("Teachers-add error: " + bindingResult.toString());
            bindingResult.rejectValue("name", "123", "Name is incorrect. Try again");

            return listOfTeachers(model, teachersModel);
        } else {
            teachersService.addTeacher(teachersModel);
            return "redirect:/teachers";
        }
    }*/

}