package com.system.edu.web.dao;

import com.system.edu.models.dao.PositionsEntity;
import com.system.edu.models.dao.UsersEntity;
import com.system.edu.models.ui.Positions;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 18.06.14
 */

@Repository
public class PositionsDao {

    @Autowired
    SessionFactory sessionFactory;

    IMergingContext context = new MergingContext();


    @Transactional
    public boolean addPosition(Positions position) {
        try {
            PositionsEntity positionsEntity = context.map(position, PositionsEntity.class);
            sessionFactory.getCurrentSession().save(positionsEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean checkPositionName(String name) {

        PositionsEntity positionsEntity = (PositionsEntity) sessionFactory.getCurrentSession().createCriteria(PositionsEntity.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return positionsEntity == null ? true : false;
    }

    @Transactional
    public void updatePosition(PositionsEntity position) {
        sessionFactory.getCurrentSession().merge(position);
    }

    @Transactional
    public Positions getPosition(int id) {
        Object object = sessionFactory.getCurrentSession().get(UsersEntity.class, id);
        Positions position = context.map(object, Positions.class);
        return position;
    }

    @Transactional
    public boolean deletePosition(int id) {
        try {
            PositionsEntity entityForDelete = (PositionsEntity) sessionFactory.getCurrentSession().createCriteria(PositionsEntity.class).add(Restrictions.eq("id", id)).uniqueResult();
            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Positions> getAllPositions() {
        List<Positions> positions = new ArrayList<>();
        try {
            List<PositionsEntity> positionsEntities = sessionFactory.getCurrentSession().createQuery("from PositionsEntity").list();
            for (PositionsEntity pos : positionsEntities) {
                Positions position = context.map(pos, Positions.class);
                positions.add(position);
            }
        } catch (Exception e) {
            //todo
        }
        return positions;
    }
}
