package com.system.edu.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;



@Component
public class LoginService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public boolean login(String login, String password, HttpServletResponse response) {
        try {
            //todo -> authentication logic(from DB: check userName and password)
        } catch (Exception e) {
            logger.error("Error while auth:", e);
            return false;
        }
        return true;
    }
}
