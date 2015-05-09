package com.system.edu.controller;

import com.system.edu.models.dao.Cycle;
import com.system.edu.web.service.CyclesService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class CycleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CyclesService cyclesService;


    @RequestMapping(value = {"/cycles"}, method = RequestMethod.GET)
    public String listOfCycles(Model model, @ModelAttribute Cycle cycleModel) {
        logger.info("IN: cycles/list-GET");

        model.addAttribute("cyclesList", cyclesService.getCycles());

        return "directories/cycle";
    }

    @RequestMapping(value = "/cycles/add", method = RequestMethod.POST)
    public String addCycle(Model model, @Valid @ModelAttribute Cycle cycleModel,
                           BindingResult result, RedirectAttributes redirectAttrs) {

        logger.info("IN: cycles/add-POST");

        if (result.hasErrors() || cyclesService.cycleExists(cycleModel.getName())) {
            logger.info("Cycle-add error: " + result.toString());
            result.rejectValue("name", "123", "Name is incorrect. Try again");
            return listOfCycles(model, cycleModel);
        } else {
            cyclesService.addCycle(cycleModel);
            return "redirect:/cycles";
        }
    }

    @RequestMapping(value = "/cycles/delete", method = RequestMethod.GET)
    public String editStrategyPage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.info("IN: Cycle/delete-GET:  ID to query = " + id);

        if (!model.containsAttribute("cycle")) {
            logger.info("Delete Cycle ");
            cyclesService.deleteCycle(id);
        }

        return "redirect:/cycles";
    }
}