package com.system.edu.controller;

import com.system.edu.web.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getList() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        HttpServletResponse response, ModelMap model) {
        logger.debug("Login request - loginName:{}", loginName);
        boolean isAuthenticated = loginService.login(loginName, password, response);
        if (isAuthenticated) {
            return "login";//todo
        } else {
            model.addAttribute("signInError", "Invalid username or password");
            model.addAttribute("userName", loginName);
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response) {
        return "redirect:/";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
