package com.system.edu.web.dao;

import com.system.edu.models.dao.PlanLessonsEntity;
import com.system.edu.models.ui.PlanLessons;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanLessonsDao {

    @Autowired
    SessionFactory sessionFactory;

    IMergingContext context = new MergingContext();

    @Transactional
    public List<PlanLessons> getLessons(int planId) {
        List<PlanLessons> plans = new ArrayList<>();
        try {
            List<PlanLessonsEntity> plansEntities = sessionFactory.getCurrentSession()
                    .createCriteria(PlanLessonsEntity.class)
                    .add(Restrictions.eq("plansByPlan.id", planId))
                    .list();

            for (PlanLessonsEntity pos : plansEntities) {
                PlanLessons plan = context.map(pos, PlanLessons.class);
                plans.add(plan);
            }
        } catch (Exception e) {
            //todo
        }
        return plans;
    }

    @Transactional
    public boolean addLesson(PlanLessonsEntity planLessonsEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(planLessonsEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}