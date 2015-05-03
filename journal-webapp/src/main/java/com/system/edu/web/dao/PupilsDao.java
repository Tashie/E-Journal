package com.system.edu.web.dao;

import com.system.edu.models.dao.PupilsEntity;
import com.system.edu.models.dao.TeachersEntity;
import com.system.edu.models.ui.Pupils;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class PupilsDao {

    IMergingContext context = new MergingContext();

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public boolean addPupil(Pupils pupils) {
        try {
            PupilsEntity pupilsEntity = context.map(pupils, PupilsEntity.class);
            sessionFactory.getCurrentSession().merge(pupilsEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkPupilFullName(String lastname, String firstname, String middlename) {

        PupilsEntity pupilsEntity = (PupilsEntity) sessionFactory.getCurrentSession().createCriteria(PupilsEntity.class).add(Restrictions.eq("lastname", lastname)).add(Restrictions.eq("firstname", firstname)).add(Restrictions.eq("middlename", middlename)).uniqueResult();

        return (pupilsEntity == null ? true : false) ;
    }

    @Transactional
    public void updatePupils(Pupils pupils) {
        PupilsEntity pupilsEntity = context.map(pupils, PupilsEntity.class);
        sessionFactory.getCurrentSession().merge(pupilsEntity);
    }

    @Transactional
    public Pupils getPupil(int id) {
        Object object = sessionFactory.getCurrentSession().get(PupilsEntity.class, id);
        Pupils pupil = context.map(object, Pupils.class);
        return pupil;
    }

    @Transactional
    public boolean deletePupil(int id) {
        try {
            PupilsEntity entityForDelete = (PupilsEntity) sessionFactory.getCurrentSession().createCriteria(PupilsEntity.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<PupilsEntity> getAllPupils() {
        return sessionFactory.getCurrentSession()
                .createCriteria(PupilsEntity.class).list();
    }

    @Transactional
    public String getPupilName(int id) {
        PupilsEntity pupilsEntity = (PupilsEntity) sessionFactory.getCurrentSession()
                .get(PupilsEntity.class, id);
        return pupilsEntity.getLastname() + " " +
                pupilsEntity.getFirstname() + " " +
                pupilsEntity.getMiddlename();
    }

}