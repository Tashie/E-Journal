package com.system.edu.web.dao;

import com.system.edu.models.dao.Role;
import com.system.edu.models.dao.UserRolesEntity;
import com.system.edu.models.dao.UsersEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
            Role role = new Role();
            role.setId(1);
            UserRolesEntity userRolesEntity = new UserRolesEntity();
            userRolesEntity.setRoleByRolecode(role);
            userRolesEntity.setUser(usersEntity);
            sessionFactory.getCurrentSession().save(usersEntity);
            sessionFactory.getCurrentSession().save(userRolesEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkUserName(String login) {
        UsersEntity user = (UsersEntity)sessionFactory.getCurrentSession()
                .createCriteria(UsersEntity.class)
                .add(Restrictions.eq("username", login))
                .uniqueResult();
        return user == null ? false : true;
    }

    @Transactional
    public boolean validateUser(String username, String uuid) {
        try {
            UsersEntity user = (UsersEntity) sessionFactory.getCurrentSession()
                    .createCriteria(UsersEntity.class)
                    .add(Restrictions.eq("username", username))
                    .add(Restrictions.eq("uuid", uuid))
                    .uniqueResult();
            if (user != null) {
                user.setEnabled((byte) 1);
                sessionFactory.getCurrentSession()
                        .update(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}