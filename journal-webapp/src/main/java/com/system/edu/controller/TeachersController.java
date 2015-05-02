package com.system.edu.controller;

import com.system.edu.models.ui.Positions;
import com.system.edu.models.ui.Teachers;
import com.system.edu.web.service.PositionsService;
import com.system.edu.web.service.TeachersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Дмитрий on 11.07.14.
 */

@Controller
public class TeachersController {
    static Logger logger = LoggerFactory.getLogger(TeachersController.class);


    @Autowired
    private TeachersService teachersService;

    @Autowired
    private PositionsService positionsService;

    @RequestMapping(value = {"/teachers"}, method = RequestMethod.GET)
    public String listOfTeachers(Model model, @ModelAttribute Teachers teachersModel) {
        logger.info("IN: teachers/list-GET");
        List<Positions> positions = positionsService.getPositions();
        model.addAttribute("positions", positions);

        List<Teachers> teachersList = teachersService.getTeachers();
        model.addAttribute("teachersList", teachersList);
        return "directories/teachers";
    }

    @RequestMapping(value = "/teachers/add", method = RequestMethod.POST)
    public String addingTeachers(Model model, @Valid @ModelAttribute Teachers teachersModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        logger.info("IN: Teachers/add-POST");

        if (!teachersService.checkIsUniqueTeacherFullName(teachersModel.getLastname(), teachersModel.getFirstname(), teachersModel.getMiddlename())) {
            bindingResult.rejectValue("lastname", "123", "Teacher with the same name already exists. Check your data again");
        }

        if(bindingResult.hasErrors()) return  listOfTeachers(model, teachersModel);

        teachersService.addTeacher(teachersModel);
        return "redirect:/teachers";
    }

}