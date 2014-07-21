package com.system.edu.controller;

import com.system.edu.models.ui.Positions;
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
    public String listOfPositions(Model model, @ModelAttribute Positions positionModel) {
        logger.info("IN: teachers/list-GET");

        List<Teachers> teachersList = teachersService.getTeachers();
        model.addAttribute("teachersList", teachersList);
        return "directories/teachers";
}
}