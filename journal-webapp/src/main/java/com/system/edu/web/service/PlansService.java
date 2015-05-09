package com.system.edu.web.service;

import com.system.edu.models.dao.Plan;
import com.system.edu.models.dao.Subject;
import com.system.edu.models.dao.Teacher;
import com.system.edu.web.dao.PlansDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlansService {

    @Autowired
    private PlansDao plansDao;

    public List<Plan> getPlans() {
        return plansDao.getAllPlans();
    }

    public boolean addPlan(Plan plans) {
        return plansDao.addPlan(plans);
    }

    public boolean isPlanExists(Plan plan) {
        return plansDao.isPlanExists(plan);
    }

    public boolean delete(Integer planId) {
        return plansDao.deletePlans(planId);
    }

    public List<Subject> getSubjects() { return plansDao.getSubjects(); }

    public List<String> getYears() { return plansDao.getYears(); }

    public List<Teacher> getTeachers() { return plansDao.getTeachers(); }

    public Plan getPlans(int teacherId, int subjectId, int year) {
        return plansDao.getPlans(teacherId, subjectId, year);
    }
}