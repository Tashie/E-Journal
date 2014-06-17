package com.system.edu.web.service;

import com.system.edu.models.dao.UsersEntity;
import com.system.edu.models.ui.Users;
import com.system.edu.web.dao.RegisterDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nata on 15.06.2014.
 */


@Component
public class RegisterService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RegisterDao registerDao;

    public boolean register(Users user) {
        try {
            IMergingContext context = new MergingContext();
            UsersEntity users = context.map(user, UsersEntity.class);
            registerDao.registerUser(users);
        } catch (Exception e) {
            logger.error("Error while add new user:", e);
            return false;
        }
        return true;
    }
}
