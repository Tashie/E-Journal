package com.system.edu.web.dao;

import com.system.edu.models.dao.Positions;
import com.system.edu.models.dao.UsersEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
public class PositionsDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public boolean addPosition(Positions positions) {
        try {
            sessionFactory.getCurrentSession().merge(positions);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkPositionName(String name) {

        Positions positions = (Positions) sessionFactory.getCurrentSession().createCriteria(Positions.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return positions == null ? true : false;
    }

    @Transactional
    public void updatePosition(Positions positions) {
        sessionFactory.getCurrentSession().merge(positions);
    }

    @Transactional
    public Positions getPosition(int id) {
        return (Positions) sessionFactory.getCurrentSession().get(UsersEntity.class, id);
    }

    @Transactional
    public boolean deletePosition(int id) {
        try {
            Positions entityForDelete = (Positions) sessionFactory.getCurrentSession().createCriteria(Positions.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Positions> getAllPositions() {
        return sessionFactory.getCurrentSession().createQuery("from Positions").list();
    }
}
