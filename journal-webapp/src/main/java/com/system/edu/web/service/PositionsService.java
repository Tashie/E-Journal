package com.system.edu.web.service;

import com.system.edu.models.dao.Positions;
import com.system.edu.web.dao.PositionsDao;
import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class PositionsService {

    @Autowired
    private PositionsDao positionDAO;

    public void addPosition(Positions positions) {
        positionDAO.addPosition(positions);
    }

    public void updatePosition(Positions positions) {
        positionDAO.updatePosition(positions);
    }

    public Positions getPosition(int id) {
        return positionDAO.getPosition(id);
    }

    public void deletePosition(int id) {
        positionDAO.deletePosition(id);
    }

    public boolean checkIsUniquePositionName(String posName) {
        return positionDAO.checkPositionName(posName);
    }

    public List<Positions> getPositions() {
        return positionDAO.getAllPositions();
    }

}
