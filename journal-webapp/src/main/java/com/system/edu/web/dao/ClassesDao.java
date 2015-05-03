package com.system.edu.web.dao;

import com.system.edu.models.dao.ClassesEntity;
import com.system.edu.models.ui.Classes;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
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

    IMergingContext context = new MergingContext();

    @Transactional
    public List<ClassesEntity> getAllClasses() {
        return sessionFactory.getCurrentSession()
                .createCriteria(ClassesEntity.class).list();
    }

    @Transactional
    public Classes getClass(int id) {
        Object object = sessionFactory.getCurrentSession().get(ClassesEntity.class, id);
        Classes pupil = context.map(object, Classes.class);
        return pupil;
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