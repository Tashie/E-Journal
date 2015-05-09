package com.system.edu.controller;

import com.system.edu.models.dao.Cycle;
import com.system.edu.models.dao.Subject;
import com.system.edu.web.service.CyclesService;
import com.system.edu.web.service.SubjectsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        List<Subject> subjects = subjectsService.getSubjects();
        model.addAttribute("subjects", subjects);

        List<Cycle> cycles = cyclesService.getCycles();
        model.addAttribute("cycles", cycles);

        return "directories/subjects";
    }

    @RequestMapping(value = {"/subjects/add"}, method = RequestMethod.GET)
    public String addSubjectForm(Subject subjects, ModelMap model) {
        logger.info("IN: subjects/add-GET");

        if (!model.containsAttribute("cycles")) {
            logger.info("Adding subject object to model");

            List<Cycle> cycles = cyclesService.getCycles();
            model.addAttribute("cycles", cycles);
        }

        return "directories/subject";
    }

    @RequestMapping(value = "/subjects/add", method = RequestMethod.POST)
    public String addCycle(@Valid @ModelAttribute Subject subject,
                            BindingResult result, ModelMap model) {

        logger.info("IN: subjects/add-POST");

        if (result.hasErrors() || !subjectsService.subjectExists(subject.getName())) {
            logger.info("Subject-add error: " + result.toString());

            List<Cycle> cycles = cyclesService.getCycles();
            model.addAttribute("cycles", cycles);

            return "directories/subject";
        } else {
            subjectsService.addSubject(subject);
            return "redirect:/subjects";
        }
    }

    @RequestMapping(value = "/subjects/edit", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void editCycle(@RequestParam("subjectId") int subjectId,
                            @RequestParam("name") String name,
                            @RequestParam("nameShorten") String nameShorten,
                            @RequestParam("difficulty") int difficulty,
                            @RequestParam("cycle") int cycleId) {
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setName(name);
        subject.setNameShorten(nameShorten);
        subject.setDifficulty(difficulty);
        Cycle cycle = new Cycle();
        cycle.setId(cycleId);
        subject.setCycleByCycle(cycle);

        subjectsService.editSubject(subject);
    }


    @RequestMapping(value = "/subjects/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Subjects/delete-GET:  ID to query = " + id);

       //todo
        return "redirect:/subjects";
    }
}