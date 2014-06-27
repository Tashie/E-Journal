package com.system.edu.controller;

import com.system.edu.models.ui.Support;
import com.system.edu.models.ui.Users;
import com.system.edu.web.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sph on 27.06.2014.
 */

@Controller
public class SupportController {

    @Autowired
    private SupportService supportService;

    @RequestMapping(value = {"/support"}, method = RequestMethod.GET)
    public String supportForm(Support support) {
        return "support";
    }

    @RequestMapping(value = {"/support"}, method = RequestMethod.POST)
    public String support(@Valid Support support,
                          BindingResult bindingResult, ModelMap map) {
        if (bindingResult.hasErrors()) {
            return "support";
        }

        final Timestamp ts = new Timestamp(System.currentTimeMillis());
        support.setMessageDate(ts);

        if (supportService.sendMessage(support)) {
            map.addAttribute("success", "The message has been sent");
            return "support";
        } else {
            map.addAttribute("success", "There was an error during the message sending");
            return "support";
        }
    }
}