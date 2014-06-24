package com.system.edu.web.dao;

import com.system.edu.models.dao.TeachersEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sph on 24.06.2014.
 */

@Repository
public class TeachersDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<TeachersEntity> getAllTeachers() {
        return sessionFactory.getCurrentSession()
                .createCriteria(TeachersEntity.class).list();
    }
}