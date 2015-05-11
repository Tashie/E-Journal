package com.system.edu.controller;


import com.system.edu.models.dao.Positions;
import com.system.edu.web.dao.PositionsDao;
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
@RequestMapping(value = {"/positions"})
public class PositionsController {
    static Logger logger = LoggerFactory.getLogger(PositionsController.class);

    @Autowired
    private PositionsDao positionsDao;

    @RequestMapping(method = RequestMethod.GET)
    public String listOfPositions(Model model, @ModelAttribute Positions positionsModel) {
        logger.info("IN: position/list-GET");

        List<Positions> positionsList = positionsDao.getAllPositions();
        model.addAttribute("positionsList", positionsList);
        return "directories/position";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addingPositions(Model model, @Valid @ModelAttribute Positions positionsModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        logger.info("IN: Positions/add-POST");

        if (bindingResult.hasErrors() || !positionsDao.checkPositionName(positionsModel.getName())) {
            logger.info("Positions-add error: " + bindingResult.toString());
            bindingResult.rejectValue("name", "123", "Name is incorrect. Try again");

            return listOfPositions(model, positionsModel);
        } else {
            positionsDao.addPosition(positionsModel);
            return "redirect:/positions";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Positions/delete-GET:  ID to query = " + id);

        if (!model.containsAttribute("position")) {
            logger.info("Delete Positions object to model");
            positionsDao.deletePosition(id);
        }

        return "redirect:/positions";
    }


}