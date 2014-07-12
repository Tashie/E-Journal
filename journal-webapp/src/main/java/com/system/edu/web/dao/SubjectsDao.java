package com.system.edu.web.dao;

import com.system.edu.models.dao.SubjectsEntity;
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
    public List<SubjectsEntity> getAllSubjects() {
        return sessionFactory.getCurrentSession()
                .createCriteria(SubjectsEntity.class).list();
    }

    @Transactional
    public boolean addSubject(SubjectsEntity subjectsEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(subjectsEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean editSubject(SubjectsEntity subjectsEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .update(subjectsEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean subjectExists(String name) {
        SubjectsEntity subjectsEntity = (SubjectsEntity)sessionFactory.getCurrentSession()
                .createCriteria(SubjectsEntity.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return subjectsEntity == null ? true : false;
    }

    @Transactional
    public String getSubjectName(int id) {
        SubjectsEntity subjectsEntity = (SubjectsEntity) sessionFactory.getCurrentSession()
                .get(SubjectsEntity.class, id);
        return subjectsEntity.getName();
    }
}