package com.system.edu.web.dao;

import com.system.edu.models.dao.TeachersEntity;
import com.system.edu.models.dao.UsersEntity;
import com.system.edu.models.ui.Teachers;
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
public class TeachersDao {

    IMergingContext context = new MergingContext();

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean addTeachers(Teachers teachers) {
        try {
            TeachersEntity teachersEntity = context.map(teachers, TeachersEntity.class);
            sessionFactory.getCurrentSession().merge(teachersEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkTeachersName(String surname) {

        TeachersEntity teachersEntity = (TeachersEntity) sessionFactory.getCurrentSession().createCriteria(TeachersEntity.class).add(Restrictions.eq("surname", surname))
                .uniqueResult();
        return teachersEntity == null ? true : false;
    }

    @Transactional
    public void updateTeachers(Teachers teachers) {
        TeachersEntity teachersEntity = context.map(teachers, TeachersEntity.class);
        sessionFactory.getCurrentSession().merge(teachersEntity);
    }

    @Transactional
    public Teachers getTeachers(int id) {
        Object object = sessionFactory.getCurrentSession().get(UsersEntity.class, id);
        Teachers teachers = context.map(object, Teachers.class);
        return teachers;
    }

    @Transactional
    public boolean deleteTeachers(int id) {
        try {
            TeachersEntity entityForDelete = (TeachersEntity) sessionFactory.getCurrentSession().createCriteria(TeachersEntity.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<TeachersEntity> getAllTeachers() {
        return sessionFactory.getCurrentSession()
                .createCriteria(TeachersEntity.class).list();
    }

    @Transactional
    public String getTeacherName(int id) {
        TeachersEntity teachersEntity = (TeachersEntity) sessionFactory.getCurrentSession()
                .get(TeachersEntity.class, id);
        return teachersEntity.getLastname() + " " +
                teachersEntity.getFirstname() + " " +
                teachersEntity.getMiddlename();
    }

}