package com.system.edu.web.service;

import com.system.edu.models.dao.PupilsEntity;
import com.system.edu.models.ui.Pupils;
import com.system.edu.web.dao.PupilsDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PupilsService {

    IMergingContext context = new MergingContext();

    @Autowired
    private PupilsDao pupilsDAO;

    public List<Pupils> getPupils() {
        List<Pupils> pupils = new ArrayList<>();
        for (PupilsEntity pupilsEntity : pupilsDAO.getAllPupils()) {
            Pupils pupil = context.map(pupilsEntity, Pupils.class);
            pupils.add(pupil);
        }
        return pupils;
    }

    public void addPupil(Pupils pupils) {
        pupilsDAO.addPupil(pupils);
    }

    public void updatePupils(Pupils pupils) {
        pupilsDAO.updatePupils(pupils);
    }

    public Pupils getPupil(int id) {
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