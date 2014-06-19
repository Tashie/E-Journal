package com.system.edu.web.dao;

import com.system.edu.models.dao.PositionsEntity;
import com.system.edu.models.dao.UsersEntity;
import com.system.edu.models.ui.Positions;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.hibernate.SessionFactory;
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

    @Transactional
    public void addPosition(PositionsEntity position) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Transactional
    public void updatePosition(PositionsEntity position) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Transactional
    public PositionsEntity getPosition(int id) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    @Transactional
    public void deletePosition(int id) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Transactional
    public List<Positions> getAllPositions() {
        List<Positions> positions = new ArrayList<>();
        try {
            List<PositionsEntity> positionsEntities = sessionFactory.getCurrentSession().createQuery("from PositionsEntity").list();
            IMergingContext context = new MergingContext();
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
