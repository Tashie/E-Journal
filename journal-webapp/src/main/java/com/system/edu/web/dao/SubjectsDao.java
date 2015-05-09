package com.system.edu.web.dao;

import com.system.edu.models.dao.Subject;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SubjectsDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<Subject> getAllSubjects() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Subject.class).list();
    }

    @Transactional
    public boolean addSubject(Subject subject) {
        try {
            sessionFactory.getCurrentSession()
                    .save(subject);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean editSubject(Subject subject) {
        try {
            sessionFactory.getCurrentSession()
                    .update(subject);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean subjectExists(String name) {
        Subject subject = (Subject)sessionFactory.getCurrentSession()
                .createCriteria(Subject.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return subject == null ? true : false;
    }

    @Transactional
    public String getSubjectName(int id) {
        Subject subject = (Subject) sessionFactory.getCurrentSession()
                .get(Subject.class, id);
        return subject.getName();
    }
}