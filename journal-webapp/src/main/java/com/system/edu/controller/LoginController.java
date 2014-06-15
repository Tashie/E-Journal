package com.system.edu.controller;

import com.system.edu.models.ui.Users;
import com.system.edu.web.service.LoginService;
import com.system.edu.web.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RegisterService registerService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid Users user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        registerService.register(user);
        return "redirect:/login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Users users) {
        return "register";
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
