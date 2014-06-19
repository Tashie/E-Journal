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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PositionsController {
    static Logger logger = LoggerFactory.getLogger(PositionsController.class);

    @Autowired
    private PositionsService positionsService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/positions"}, method = RequestMethod.GET)
    public String listOfStrategies(Model model) {
        logger.info("IN: position/list-GET");

        List<Positions> positions = positionsService.getPositions();
        model.addAttribute("positions", positions);


        if (!model.containsAttribute("position")) {
            logger.info("Adding position object to model");
            Positions position = new Positions();
            model.addAttribute("position", position);
        }
        return "position";
    }

}