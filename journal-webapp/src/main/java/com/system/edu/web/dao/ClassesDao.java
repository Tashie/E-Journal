package com.system.edu.web.dao;

import com.system.edu.models.dao.ClassesEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sph on 24.06.2014.
 */

@Repository
public class ClassesDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<ClassesEntity> getAllClasses() {
        return sessionFactory.getCurrentSession()
                .createCriteria(ClassesEntity.class).list();
    }

    @Transactional
    public boolean addClass(ClassesEntity classesEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(classesEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean classExists(String name) {
        ClassesEntity classesEntity = (ClassesEntity)sessionFactory.getCurrentSession()
                .createCriteria(ClassesEntity.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return classesEntity == null ? false : true;
    }
}