package com.system.edu.controller;

/**
 * User: nata
 * Time: 23:02
 */

import java.util.List;

import com.system.edu.models.ui.Positions;
import com.system.edu.web.service.PositionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class PositionsController {
    static Logger logger = LoggerFactory.getLogger(PositionsController.class);

    @Autowired
    private PositionsService positionsService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/positions"}, method = RequestMethod.GET)
    public String listOfPositions(Model model) {
        logger.info("IN: position/list-GET");

        List<Positions> positions = positionsService.getPositions();
        model.addAttribute("positionsList", positions);


        if (!model.containsAttribute("positions")) {
            logger.info("Adding position object to model");
            Positions position = new Positions();
            model.addAttribute("positions", position);
        }
        return "directories/position";
    }


    @RequestMapping(value = "/positions/add", method = RequestMethod.POST)
    public String addingPositions(@Valid @ModelAttribute Positions position,
                                  BindingResult result, RedirectAttributes redirectAttrs) {

        logger.info("IN: Position/add-POST");

        if (result.hasErrors() || !positionsService.checkIsUniquePositionName(position.getName())) {
            logger.info("Position-add error: " + result.toString());
            return "directories/position";
        } else {
            positionsService.addPosition(position);
            return "redirect:/positions";
        }
    }

    @RequestMapping(value = "/positions/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Position/delete-GET:  ID to query = " + id);

        if (!model.containsAttribute("position")) {
            logger.info("Delete Position object to model");
            positionsService.deletePosition(id);
        }

        return "redirect:/positions";
    }


}