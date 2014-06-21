package com.system.edu.controller;

import com.system.edu.models.ui.Users;
import com.system.edu.web.dao.RegisterDao;
import com.system.edu.web.service.HashService;
import com.system.edu.web.service.MailService;
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
import java.util.UUID;

@Controller
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RegisterService registerService;

    @Autowired
    private HashService hashService;

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private MailService mailService;

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

        if (registerDao.checkUserName(user.getUsername())) {
            bindingResult.rejectValue("username", "123", "This name is already registered");
        }

        String userPassword = user.getPassword();

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
            return "auth/register";
        }

        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);

        if (registerService.register(user)) {
            String subject = "Thank you " + user.getFirstname() + " " + user.getLastname()
                    + " for registration";
            String content = "Thank you " + user.getFirstname() + " " + user.getLastname()
                    + " for registration, "
                    + "Following are the details of your account,"
                    + "<br/>*************************************** "
                    + "<br/>Username : <b>" + user.getUsername() + "</b>"
                    + "<br/>Password : <b>" + userPassword + "</b>"
                    + "<br/>Name : <b>" + user.getFirstname() + " " + user.getLastname() + "</b>"
                    + "<br/>Click the link to activate your account : "
                    + "<a href='http://localhost:8080/edu-system/validation?username=" + user.getUsername()
                    + "&uuid=" + uuid + "'>Activate Account</a>"
                    + "<br/>*************************************** ";

            if (mailService.sendMail(subject, content, user.getUsername())) {
                return "redirect:/register_successful";
            } else {
                model.addAttribute("captcha", "There was an error during the email sending");
                bindingResult.reject("captcha", "There was an error during the email sending");
                return "auth/register";
            }
        } else {
            model.addAttribute("captcha", "Error while storing data into the database");
            bindingResult.rejectValue("captcha", "123", "Error while storing data into the database");
            return "auth/register";
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Users users) {
        return "auth/register";
    }

    @RequestMapping(value = "/register_successful", method = RequestMethod.GET)
    public String success(ModelMap map) {
        map.addAttribute("success", "Your account was added into the database. Please check your email to finish the registration process.");
        return "auth/register_successful";
    }

    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public String validate(@RequestParam("username") String username,
                           @RequestParam("uuid") String uuid,
                           ModelMap map) {
        if (registerDao.validateUser(username, uuid)) {
            map.addAttribute("success", "The registration was successful. You can now log in.");
        } else {
            map.addAttribute("success", "User was not validated. You will not be able to log in.");
        }
        return "auth/register_successful";
    }
}