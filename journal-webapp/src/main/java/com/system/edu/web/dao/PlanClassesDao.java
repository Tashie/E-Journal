package com.system.edu.web.dao;

import com.system.edu.models.dao.PlanClassesEntity;
import com.system.edu.models.ui.PlanClasses;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sph on 11.07.2014.
 */

@Repository
public class PlanClassesDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<PlanClassesEntity> getClasses(String tid, String sid, String year) {
        return sessionFactory.getCurrentSession()
                .createCriteria(PlanClassesEntity.class)
                .createAlias("plansByPlan", "plan")
                .createAlias("plan.teachersByTeacher", "t")
                .createAlias("plan.subjectsBySubject", "s")
                .add(Restrictions.and(
                        Restrictions.eq("t.id", Integer.parseInt(tid)),
                        Restrictions.eq("s.id", Integer.parseInt(sid)),
                        Restrictions.eq("plan.year", Integer.parseInt(year))
                ))
                .list();
    }

    @Transactional
    public boolean addPlanClass(PlanClassesEntity planClassesEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(planClassesEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean isPlanExists(PlanClasses plansClasses) {
        PlanClassesEntity plansEntity = (PlanClassesEntity) sessionFactory.getCurrentSession()
                .createCriteria(PlanClassesEntity.class)
                .add(Restrictions.eq("plansByPlan.id", plansClasses.getId()))
                .uniqueResult();
        return plansEntity != null ? true : false;
    }
}