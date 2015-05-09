package com.system.edu.web.dao;

import com.system.edu.models.dao.PlanClass;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PlanClassesDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<PlanClass> getClasses(String tid, String sid, String year) {
        return sessionFactory.getCurrentSession()
                .createCriteria(PlanClass.class)
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
    public boolean addPlanClass(PlanClass planClass) {
        try {
            sessionFactory.getCurrentSession()
                    .save(planClass);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean isPlanExists(PlanClass plansClass) {
        PlanClass plansEntity = (PlanClass) sessionFactory.getCurrentSession()
                .createCriteria(PlanClass.class)
                .add(Restrictions.eq("plansByPlan.id", plansClass.getId()))
                .uniqueResult();
        return plansEntity != null ? true : false;
    }
}