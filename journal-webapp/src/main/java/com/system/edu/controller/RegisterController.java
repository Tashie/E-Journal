package com.system.edu.controller;

import com.system.edu.models.ui.Users;
import com.system.edu.web.service.HashService;
import com.system.edu.web.service.RegisterService;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RegisterService registerService;

    @Autowired
    private HashService hashService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid Users user, BindingResult bindingResult, ModelMap model,
                               @RequestParam("check_password") String confirm,
                               @RequestParam("recaptcha_challenge_field") String user_captcha,
                               @RequestParam("recaptcha_response_field") String original_captcha,
                               HttpServletRequest request) {

        String user_address = request.getRemoteAddr();
        ReCaptchaImpl recaptcha = new ReCaptchaImpl();
        recaptcha.setPrivateKey("6LcmHssSAAAAALGlVKT8Stiw6saeagGd5I_tHDOb");

        ReCaptchaResponse response = recaptcha.checkAnswer(user_address, user_captcha, original_captcha);

        if (!response.isValid()) {
            model.addAttribute("captcha", "Captcha verification failed");
            bindingResult.reject("captcha", "Captcha failed");
        }

        if (user.getPassword().compareTo(confirm) != 0) {
            model.addAttribute("check_password", "Passwords have to be the same");
            bindingResult.reject("check_password", "Passwords have to be the same");
        }

        if (!user.getPassword().isEmpty()) {
            String hashPassword = hashService.hash(user.getPassword());
            if (hashPassword.isEmpty()) {
                bindingResult.rejectValue("password", "123", "Password is incorrect. Try again");
            }
            else {
                user.setPassword(hashPassword);
            }
        }

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
}