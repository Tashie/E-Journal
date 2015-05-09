package com.system.edu.web.dao;

import com.system.edu.models.dao.Cycle;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CyclesDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<Cycle> getAllCycles() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Cycle.class).list();
    }

    @Transactional
    public boolean addCycle(Cycle cycleEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .merge(cycleEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean cycleExists(String name) {
        Cycle positionsEntity = (Cycle) sessionFactory.getCurrentSession()
                .createCriteria(Cycle.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return positionsEntity == null ? false : true;
    }

    @Transactional
    public boolean deleteCycle(int id) {
        try {
            Cycle entityForDelete = (Cycle) sessionFactory.getCurrentSession().createCriteria(Cycle.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}