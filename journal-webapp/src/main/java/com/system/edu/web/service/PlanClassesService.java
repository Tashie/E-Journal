package com.system.edu.web.service;

import com.system.edu.models.dao.PlanClass;
import com.system.edu.web.dao.PlanClassesDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class PlanClassesService {

    IMergingContext context = new MergingContext();

    @Autowired
    private PlanClassesDao planClassesDao;

    public List<PlanClass> getClasses(String tid, String sid, String year) {
        return planClassesDao.getClasses(tid, sid, year);
    }

    public boolean addPlanClass(PlanClass planClass) {
        return planClassesDao.addPlanClass(planClass);
    }

    public boolean isPlanExists(PlanClass plan) {
        return planClassesDao.isPlanExists(plan);
    }
}