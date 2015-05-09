package com.system.edu.web.service;

import com.system.edu.models.dao.Pupil;
import com.system.edu.web.dao.PupilsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PupilsService {

    @Autowired
    private PupilsDao pupilsDAO;

    public List<Pupil> getPupils() {
        return  pupilsDAO.getAllPupils();
    }

    public void addPupil(Pupil pupils) {
        pupilsDAO.addPupil(pupils);
    }

    public void updatePupils(Pupil pupils) {
        pupilsDAO.updatePupil(pupils);
    }

    public Pupil getPupil(int id) {
        return pupilsDAO.getPupil(id);
    }

    public void deletePupil(int id) {
        pupilsDAO.deletePupil(id);
    }

    public boolean checkIsUniquePupilFullName(String lastname, String firstname, String middlename) {
        return pupilsDAO.checkPupilFullName(lastname, firstname, middlename);
    }


    public String getPupilName(int id) {
        return pupilsDAO.getPupilName(id);
    }

}