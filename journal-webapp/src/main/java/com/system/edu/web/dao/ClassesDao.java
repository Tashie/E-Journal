package com.system.edu.web.dao;

import com.system.edu.models.dao.Classes;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ClassesDao {

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public List<Classes> getAllClasses() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Classes.class).list();
    }

    @Transactional
    public Classes getClass(int id) {
        return (Classes) sessionFactory.getCurrentSession().get(Classes.class, id);
    }

    @Transactional
    public boolean addClass(Classes classesEntity) {
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
        Classes classesEntity = (Classes)sessionFactory.getCurrentSession()
                .createCriteria(Classes.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return classesEntity == null ? false : true;
    }


    @Transactional
    public boolean deleteClass(int id) {
        try {
            Classes entityForDelete = (Classes) sessionFactory.getCurrentSession().createCriteria(Classes.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}