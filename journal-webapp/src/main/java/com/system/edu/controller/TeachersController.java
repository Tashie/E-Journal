package com.system.edu.controller;

import com.system.edu.models.dao.Positions;
import com.system.edu.models.dao.Teacher;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value="/teachers")
public class TeachersController {
    static Logger logger = LoggerFactory.getLogger(TeachersController.class);


    @Autowired
    private TeachersService teachersService;

    @Autowired
    private PositionsService positionsService;

    @RequestMapping(method = RequestMethod.GET)
    public String listOfTeachers(Model model, @ModelAttribute Teacher teachersModel) {
        logger.info("IN: teachers/list-GET");
        List<Positions> positions = positionsService.getPositions();
        model.addAttribute("positions", positions);

        List<Teacher> teachersList = teachersService.getTeachers();
        model.addAttribute("teachersList", teachersList);
        return "directories/teachers";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addingTeachers(Model model, @Valid @ModelAttribute Teacher teachersModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        logger.info("IN: Teachers/add-POST");

        if (!teachersService.checkIsUniqueTeacherFullName(teachersModel.getLastname(), teachersModel.getFirstname(), teachersModel.getMiddlename())) {
            bindingResult.rejectValue("lastname", "123", "Teacher with the same name already exists. Check your data again");
        }

        if(bindingResult.hasErrors()) return  listOfTeachers(model, teachersModel);

        teachersService.addTeacher(teachersModel);
        return "redirect:/teachers";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Teachers/delete-GET:  ID to query = " + id);

        if (!model.containsAttribute("teachers")) {
            logger.info("Delete Teachers object to model");
            teachersService.deleteTeacher(id);
        }

        return "redirect:/teachers";
    }

}