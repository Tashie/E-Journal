package com.system.edu.web.dao;

import com.system.edu.models.dao.SupportEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sph on 27.06.2014.
 */

@Repository
public class SupportDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public boolean sendMessage(SupportEntity supportEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(supportEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}