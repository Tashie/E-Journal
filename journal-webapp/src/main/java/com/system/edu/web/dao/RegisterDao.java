package com.system.edu.web.dao;

import com.system.edu.models.dao.UsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nata on 15.06.2014.
 */

@Repository
public class RegisterDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public boolean registerUser(UsersEntity usersEntity) {
        try {
            sessionFactory.getCurrentSession().save(usersEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
