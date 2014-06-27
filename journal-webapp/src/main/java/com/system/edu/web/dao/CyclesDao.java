package com.system.edu.web.dao;

import com.system.edu.models.dao.CyclesEntity;
import com.system.edu.models.dao.PositionsEntity;
import com.system.edu.models.ui.Cycles;
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
    public List<CyclesEntity> getAllCycles() {
        return sessionFactory.getCurrentSession()
                .createCriteria(CyclesEntity.class).list();
    }

    @Transactional
    public boolean addCycle(CyclesEntity cycleEntity) {
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
        CyclesEntity positionsEntity = (CyclesEntity) sessionFactory.getCurrentSession()
                .createCriteria(CyclesEntity.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return positionsEntity == null ? false : true;
    }

    @Transactional
    public boolean deleteCycle(int id) {
        try {
            CyclesEntity entityForDelete = (CyclesEntity) sessionFactory.getCurrentSession().createCriteria(CyclesEntity.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}