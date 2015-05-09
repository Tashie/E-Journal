package com.system.edu.web.dao;

import com.system.edu.models.dao.Teacher;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class TeachersDao {

    IMergingContext context = new MergingContext();

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public boolean addTeacher(Teacher teacher) {
        try {
            sessionFactory.getCurrentSession().merge(teacher);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkTeachersFullName(String lastname, String firstname, String middlename) {

        Teacher teacher = (Teacher) sessionFactory.getCurrentSession().createCriteria(Teacher.class).add(Restrictions.eq("lastname", lastname)).add(Restrictions.eq("firstname", firstname)).add(Restrictions.eq("middlename", middlename)).uniqueResult();

        return (teacher == null ? true : false) ;
    }

    @Transactional
    public void updateTeacher(Teacher teacher) {
        sessionFactory.getCurrentSession().merge(teacher);
    }

    @Transactional
    public Teacher getTeacher(int id) {
        return (Teacher) sessionFactory.getCurrentSession().get(Teacher.class, id);
    }

    @Transactional
    public boolean deleteTeachers(int id) {
        try {
            Teacher entityForDelete = (Teacher) sessionFactory.getCurrentSession().createCriteria(Teacher.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Teacher> getAllTeachers() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Teacher.class).list();
    }

    @Transactional
    public String getTeacherName(int id) {
        Teacher teacher = (Teacher) sessionFactory.getCurrentSession()
                .get(Teacher.class, id);
        return teacher.getLastname() + " " +
                teacher.getFirstname() + " " +
                teacher.getMiddlename();
    }

}