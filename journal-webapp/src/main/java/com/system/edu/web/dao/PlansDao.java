package com.system.edu.web.dao;

import com.system.edu.models.dao.Plan;
import com.system.edu.models.dao.Subject;
import com.system.edu.models.dao.Teacher;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PlansDao {
    @Autowired
    SessionFactory sessionFactory;

    IMergingContext context = new MergingContext();


    @Transactional
    public boolean addPlan(Plan plan) {
        try {
            sessionFactory.getCurrentSession()
                    .merge(plan);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean isPlanExists(Plan plans) {

        Plan plan = (Plan) sessionFactory.getCurrentSession()
                .createCriteria(Plan.class)
                .add(Restrictions.eq("subjectsBySubject.id", plans.getSubjectsBySubject().getId()))
                .add(Restrictions.eq("teachersByTeacher.id", plans.getTeachersByTeacher().getId()))
                .uniqueResult();
        return plan != null ? true : false;
    }



    @Transactional
    public Plan getPlans(int id) {
        return (Plan) sessionFactory.getCurrentSession().get(Plan.class, id);
    }

    @Transactional
    public boolean deletePlans(int id) {
        try {
            Plan entityForDelete = (Plan) sessionFactory.getCurrentSession()
                    .createCriteria(Plan.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Plan> getAllPlans() {
        List<Plan> plans = new ArrayList<>();
        try {
            List<Plan> plansEntities = sessionFactory.getCurrentSession()
                    .createCriteria(Plan.class)
                    .list();

            for (Plan pos : plansEntities) {
                Plan plan = context.map(pos, Plan.class);
                plans.add(plan);
            }
        } catch (Exception e) {
            //todo
        }
        return plans;
    }

    @Transactional
    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
            subjects = sessionFactory.getCurrentSession()
                    .createCriteria(Plan.class)
                    .setProjection(Projections.distinct(Projections.property("subjectsBySubject")))
                    .list();

        } catch (Exception e) {
            //todo
        }
        return subjects;
    }

    @Transactional
    public List<String> getYears() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Plan.class)
                .setProjection(Projections.distinct(Projections.property("year")))
                .list();
    }

    @Transactional
    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers= sessionFactory.getCurrentSession()
                    .createCriteria(Plan.class)
                    .setProjection(Projections.distinct(Projections.property("teachersByTeacher")))
                    .list();
        } catch (Exception e) {
            //todo
        }
        return teachers;
    }

    @Transactional
    public Plan getPlans(int teacherId, int subjectId, int year) {
        Plan plan = (Plan) sessionFactory.getCurrentSession()
                .createCriteria(Plan.class)
                .add(Restrictions.eq("subjectsBySubject.id", subjectId))
                .add(Restrictions.eq("teachersByTeacher.id", teacherId))
                .add(Restrictions.eq("year", year))
                .uniqueResult();
        return plan;
    }
}