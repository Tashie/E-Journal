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
    public String listOfCycles(Model model) {
        logger.info("IN: cycles/list-GET");

        List<Cycles> cycles = cyclesService.getCycles();
        model.addAttribute("cycles", cycles);

        if (!model.containsAttribute("cycle")) {
            logger.info("Adding cycle object to model");
            Cycles cycle = new Cycles();
            model.addAttribute("cycle", cycle);
        }

        return "directories/cycle";
    }

    @RequestMapping(value = "/cycles/add", method = RequestMethod.POST)
    public String addCycle(@Valid @ModelAttribute Cycles cycle,
                                  BindingResult result, RedirectAttributes redirectAttrs) {

        logger.info("IN: cycles/add-POST");

        if (result.hasErrors() || !cyclesService.cycleExists(cycle.getName())) {
            logger.info("Cycle-add error: " + result.toString());
            return "directories/cycles";
        } else {
            cyclesService.addCycle(cycle);
            return "redirect:/cycles";
        }
    }
}