package com.system.edu.web.service;

import com.system.edu.models.dao.PlanLessonsEntity;
import com.system.edu.models.ui.PlanLessons;
import com.system.edu.models.ui.Plans;
import com.system.edu.web.dao.PlanLessonsDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanLessonsService {

    @Autowired
    PlanLessonsDao planLessonsDao;

    @Autowired
    PlansService plansService;

    IMergingContext context = new MergingContext();

    public List<PlanLessons> getLessons(int planId) {
        return planLessonsDao.getLessons(planId);
    }

    public boolean addLesson(int planId, PlanLessons planLessons) {
        Plans plan = plansService.getPlan(planId);
        planLessons.setPlansByPlan(plan);
        PlanLessonsEntity planLessonsEntity = context.map(planLessons, PlanLessonsEntity.class);
        return planLessonsDao.addLesson(planLessonsEntity);
    }
}