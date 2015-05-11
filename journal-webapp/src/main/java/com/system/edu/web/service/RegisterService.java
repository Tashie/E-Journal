package com.system.edu.web.service;

import com.system.edu.models.dao.User;
import com.system.edu.web.dao.RegisterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class RegisterService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RegisterDao registerDao;

    public boolean register(User user) {
        try {
            registerDao.registerUser(user);
        } catch (Exception e) {
            logger.error("Error while add new user:", e);
            return false;
        }
        return true;
    }
}