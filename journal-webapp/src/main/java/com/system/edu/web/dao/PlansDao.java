package com.system.edu.web.dao;

import com.system.edu.models.dao.PlansEntity;
import com.system.edu.models.dao.SubjectsEntity;
import com.system.edu.models.dao.TeachersEntity;
import com.system.edu.models.ui.Plans;
import com.system.edu.models.ui.Subjects;
import com.system.edu.models.ui.Teachers;
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

/**
 * User: nata
 * Date: 27.06.14
 */

@Repository
public class PlansDao {
    @Autowired
    SessionFactory sessionFactory;

    IMergingContext context = new MergingContext();


    @Transactional
    public boolean addPlan(Plans plan) {
        try {
            PlansEntity plansEntity = context.map(plan, PlansEntity.class);
            sessionFactory.getCurrentSession()
                    .merge(plansEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean isPlanExists(Plans plans) {

        PlansEntity plansEntity = (PlansEntity) sessionFactory.getCurrentSession()
                .createCriteria(PlansEntity.class)
                .add(Restrictions.eq("subjectsBySubject.id", plans.getSubjectsBySubject().getId()))
                .add(Restrictions.eq("teachersByTeacher.id", plans.getTeachersByTeacher().getId()))
                .uniqueResult();
        return plansEntity != null ? true : false;
    }



    @Transactional
    public Plans getPlans(int id) {
        Object object = sessionFactory.getCurrentSession().get(PlansEntity.class, id);
        Plans plans = context.map(object, Plans.class);
        return plans;
    }

    @Transactional
    public boolean deletePlans(int id) {
        try {
            PlansEntity entityForDelete = (PlansEntity) sessionFactory.getCurrentSession()
                    .createCriteria(PlansEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            sessionFactory.getCurrentSession().delete(entityForDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Plans> getAllPlans() {
        List<Plans> plans = new ArrayList<>();
        try {
            List<PlansEntity> plansEntities = sessionFactory.getCurrentSession()
                    .createCriteria(PlansEntity.class)
                    .list();

            for (PlansEntity pos : plansEntities) {
                Plans plan = context.map(pos, Plans.class);
                plans.add(plan);
            }
        } catch (Exception e) {
            //todo
        }
        return plans;
    }

    @Transactional
    public List<Subjects> getSubjects() {
        List<Subjects> subjects = new ArrayList<>();
        try {
            List<SubjectsEntity> subjectsEntities = sessionFactory.getCurrentSession()
                    .createCriteria(PlansEntity.class)
                    .setProjection(Projections.distinct(Projections.property("subjectsBySubject")))
                    .list();
            for (SubjectsEntity subj : subjectsEntities) {
                Subjects subject = context.map(subj, Subjects.class);
                subjects.add(subject);
            }
        } catch (Exception e) {
            //todo
        }
        return subjects;
    }

    @Transactional
    public List<String> getYears() {
        return sessionFactory.getCurrentSession()
                .createCriteria(PlansEntity.class)
                .setProjection(Projections.distinct(Projections.property("year")))
                .list();
    }

    @Transactional
    public  List<Teachers> getTeachers() {
        List<Teachers> teachers = new ArrayList<>();
        try {
            List<TeachersEntity> teachersEntities = sessionFactory.getCurrentSession()
                    .createCriteria(PlansEntity.class)
                    .setProjection(Projections.distinct(Projections.property("teachersByTeacher")))
                    .list();
            for (TeachersEntity tchr : teachersEntities) {
                Teachers teacher = context.map(tchr, Teachers.class);
                teachers.add(teacher);
            }
        } catch (Exception e) {
            //todo
        }
        return teachers;
    }
}