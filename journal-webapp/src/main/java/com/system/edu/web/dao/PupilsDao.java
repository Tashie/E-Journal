package com.system.edu.web.dao;

import com.system.edu.models.dao.Pupil;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class PupilsDao {


    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public boolean addPupil(Pupil pupil) {
        try {
            sessionFactory.getCurrentSession().merge(pupil);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkPupilFullName(String lastname, String firstname, String middlename) {

        Pupil pupil = (Pupil) sessionFactory.getCurrentSession().createCriteria(Pupil.class).add(Restrictions.eq("lastname", lastname)).add(Restrictions.eq("firstname", firstname)).add(Restrictions.eq("middlename", middlename)).uniqueResult();

        return (pupil == null ? true : false) ;
    }

    @Transactional
    public void updatePupil(Pupil pupil) {
        sessionFactory.getCurrentSession().merge(pupil);
    }

    @Transactional
    public Pupil getPupil(int id) {
        return (Pupil) sessionFactory.getCurrentSession().get(Pupil.class, id);
    }

    @Transactional
    public boolean deletePupil(int id) {
        try {
            Pupil entityForDelete = (Pupil) sessionFactory.getCurrentSession().createCriteria(Pupil.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Pupil> getAllPupils() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Pupil.class).list();
    }

    @Transactional
    public String getPupilName(int id) {
        Pupil pupil = (Pupil) sessionFactory.getCurrentSession()
                .get(Pupil.class, id);
        return pupil.getLastname() + " " +
                pupil.getFirstname() + " " +
                pupil.getMiddlename();
    }

}