package com.system.edu.controller;

import com.system.edu.models.dao.Classes;
import com.system.edu.models.dao.Pupil;
import com.system.edu.web.dao.ClassesDao;
import com.system.edu.web.dao.PupilsDao;
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
    private ClassesDao classesDao;

    @Autowired
    private PupilsDao pupilsDao;


    @RequestMapping(method = RequestMethod.GET)
    public String listOfPupils(Model model) {
        logger.info("IN: pupils/list-GET");

        List<Classes> classesList = classesDao.getAllClasses();
        model.addAttribute("classes", classesList);

        List<Pupil> pupilsList = pupilsDao.getAllPupils();
        model.addAttribute("pupils", pupilsList);

        return "directories/pupils";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addPupilForm(Pupil pupil, ModelMap model) {
        logger.info("IN: pupils/add-GET");

        if (!model.containsAttribute("classes")) {
            logger.info("Adding pupil object to model");

            List<Classes> classesList = classesDao.getAllClasses();
            model.addAttribute("classes", classesList);
        }

        return "directories/pupil";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPupil(@Valid @ModelAttribute Pupil pupil,
                           BindingResult result, ModelMap model) {

        logger.info("IN: pupils/add-POST");
        pupilsDao.addPupil(pupil);
        return "redirect:/pupils";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void editPupil(@RequestParam("pupilId") int pupilId,
                          @RequestParam("firstname") String firstname,
                          @RequestParam("lastname") String lastname,
                          @RequestParam("middlename") String middlename,
                          @RequestParam("address") String address,
                          @RequestParam("classId") int classId) {

        Pupil pupil = pupilsDao.getPupil(pupilId);
        pupil.setId(pupilId);
        pupil.setFirstname(firstname);
        pupil.setLastname(lastname);
        pupil.setMiddlename(middlename);
        pupil.setClassesByClazz(classesDao.getClass(classId));

        pupilsDao.updatePupil(pupil);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePupil(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Pupils/delete-GET:  ID to query = " + id);
        pupilsDao.deletePupil(id);
        return "redirect:/pupils";
    }
}