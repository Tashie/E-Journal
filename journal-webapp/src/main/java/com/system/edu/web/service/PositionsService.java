package com.system.edu.web.service;

import com.system.edu.models.dao.PositionsEntity;
import com.system.edu.models.ui.Positions;
import com.system.edu.web.dao.PositionsDao;
import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 18.06.14
 */

@Component
@Transactional
public class PositionsService {

    @Autowired
    private PositionsDao positionDAO;

    public void addPosition(PositionsEntity position) {
        positionDAO.addPosition(position);
    }

    public void updatePosition(PositionsEntity position) {
        positionDAO.updatePosition(position);
    }

    public PositionsEntity getPosition(int id) {
        return positionDAO.getPosition(id);
    }

    public void deletePosition(int id) {
        positionDAO.deletePosition(id);
    }


    public List<Positions> getPositions() {
        return positionDAO.getAllPositions();
    }

}
