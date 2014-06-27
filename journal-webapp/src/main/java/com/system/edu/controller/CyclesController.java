package com.system.edu.controller;

import com.system.edu.models.ui.Cycles;
import com.system.edu.web.service.CyclesService;
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
import java.util.List;

@Controller
public class CyclesController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CyclesService cyclesService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/cycles"}, method = RequestMethod.GET)
    public String listOfCycles(Model model, @ModelAttribute Cycles cycleModel) {
        logger.info("IN: cycles/list-GET");

        List<Cycles> cyclesList = cyclesService.getCycles();
        model.addAttribute("cyclesList", cyclesList);

        return "directories/cycle";
    }

    @RequestMapping(value = "/cycles/add", method = RequestMethod.POST)
    public String addCycle(Model model, @Valid @ModelAttribute Cycles cycleModel,
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
            logger.info("Delete Position object to model");
            cyclesService.deleteCycle(id);
        }

        return "redirect:/cycles";
    }
}