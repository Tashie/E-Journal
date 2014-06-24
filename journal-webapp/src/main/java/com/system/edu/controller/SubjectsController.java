package com.system.edu.controller;

import com.system.edu.models.ui.Cycles;
import com.system.edu.models.ui.Subjects;
import com.system.edu.web.service.CyclesService;
import com.system.edu.web.service.SubjectsService;
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
import java.util.List;

@Controller
public class SubjectsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private CyclesService cyclesService;

    @RequestMapping(value = {"/subjects"}, method = RequestMethod.GET)
    public String listOfSubjects(Model model) {
        logger.info("IN: cycles/list-GET");

        List<Subjects> subjects = subjectsService.getSubjects();
        model.addAttribute("subjects", subjects);

        return "directories/subjects";
    }

    @RequestMapping(value = {"/subjects/add"}, method = RequestMethod.GET)
    public String addSubjectForm(Subjects subjects, ModelMap model) {
        logger.info("IN: subjects/add-GET");

        if (!model.containsAttribute("cycles")) {
            logger.info("Adding subject object to model");

            List<Cycles> cycles = cyclesService.getCycles();
            model.addAttribute("cycles", cycles);
        }

        return "directories/subject";
    }

    @RequestMapping(value = "/subjects/add", method = RequestMethod.POST)
     public String addCycle(@Valid @ModelAttribute Subjects subject,
                            BindingResult result, ModelMap model) {

        logger.info("IN: subjects/add-POST");

        if (result.hasErrors() || !subjectsService.subjectExists(subject.getName())) {
            logger.info("Subject-add error: " + result.toString());

            List<Cycles> cycles = cyclesService.getCycles();
            model.addAttribute("cycles", cycles);

            return "directories/subject";
        } else {
            subjectsService.addSubject(subject);
            return "redirect:/subjects";
        }
    }
}