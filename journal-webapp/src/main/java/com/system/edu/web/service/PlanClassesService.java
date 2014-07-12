package com.system.edu.web.service;

import com.system.edu.models.dao.PlanClassesEntity;
import com.system.edu.models.ui.Classes;
import com.system.edu.models.ui.PlanClasses;
import com.system.edu.web.dao.PlanClassesDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sph on 11.07.2014.
 */

@Component
public class PlanClassesService {

    IMergingContext context = new MergingContext();

    @Autowired
    private PlanClassesDao planClassesDao;

    public List<PlanClasses> getClasses(String tid, String sid, String year) {
        List<PlanClasses> classes = new ArrayList<>();
        for (PlanClassesEntity classesEntity : planClassesDao.getClasses(tid, sid, year)) {
            PlanClasses clazz = context.map(classesEntity, PlanClasses.class);
            classes.add(clazz);
        }
        return classes;
    }

    public boolean addPlanClass(PlanClasses planClasses) {
        PlanClassesEntity planClassesEntity = context.map(planClasses, PlanClassesEntity.class);
        return planClassesDao.addPlanClass(planClassesEntity);
    }

    public boolean isPlanExists(PlanClasses plan) {
        return planClassesDao.isPlanExists(plan);
    }
}