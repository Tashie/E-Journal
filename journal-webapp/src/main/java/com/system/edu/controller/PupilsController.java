package com.system.edu.controller;

import com.system.edu.models.dao.Classes;
import com.system.edu.models.dao.Pupil;
import com.system.edu.web.service.ClassesService;
import com.system.edu.web.service.PupilsService;
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
@RequestMapping(value = {"/pupils"})
public class PupilsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClassesService classesService;

    @Autowired
    private PupilsService pupilsService;


    @RequestMapping(method = RequestMethod.GET)
    public String listOfPupils(Model model) {
        logger.info("IN: pupils/list-GET");

        List<Classes> classesList = classesService.getClasses();
        model.addAttribute("classes", classesList);

        List<Pupil> pupilsList = pupilsService.getPupils();
        model.addAttribute("pupils", pupilsList);

        return "directories/pupils";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addPupilForm(Pupil pupil, ModelMap model) {
        logger.info("IN: pupils/add-GET");

        if (!model.containsAttribute("classes")) {
            logger.info("Adding pupil object to model");

            List<Classes> classesList = classesService.getClasses();
            model.addAttribute("classes", classesList);
        }

        return "directories/pupil";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCycle(@Valid @ModelAttribute Pupil pupil,
                           BindingResult result, ModelMap model) {

        logger.info("IN: pupils/add-POST");
        pupilsService.addPupil(pupil);
        return "redirect:/pupils";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void editCycle(@RequestParam("pupilId") int pupilId,
                          @RequestParam("firstname") String firstname,
                          @RequestParam("lastname") String lastname,
                          @RequestParam("middlename") String middlename,
                          @RequestParam("address") String address,
                          @RequestParam("classId") int classId) {

        Pupil pupils = pupilsService.getPupil(pupilId);
        pupils.setId(pupilId);
        pupils.setFirstname(firstname);
        pupils.setLastname(lastname);
        pupils.setMiddlename(middlename);
        pupils.setClassesByClazz(classesService.getClass(classId));

        pupilsService.updatePupils(pupils);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Pupils/delete-GET:  ID to query = " + id);
        pupilsService.deletePupil(id);
        return "redirect:/pupils";
    }
}