package com.system.edu.web.service;

import com.system.edu.models.dao.PlansEntity;
import com.system.edu.models.dao.SubjectsEntity;
import com.system.edu.models.ui.Plans;
import com.system.edu.models.ui.Subjects;
import com.system.edu.models.ui.Teachers;
import com.system.edu.web.dao.PlansDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: nata
 * Date: 27.06.14
 */

@Component
public class PlansService {
    IMergingContext context = new MergingContext();

    @Autowired
    private PlansDao plansDao;

    public List<Plans> getPlans() {
        return plansDao.getAllPlans();
    }

    public boolean addPlan(Plans plans) {
        return plansDao.addPlan(plans);
    }

    public boolean isPlanExists(Plans plan) {
        return plansDao.isPlanExists(plan);
    }

    public boolean delete(Integer planId) {
        return plansDao.deletePlans(planId);
    }

    public List<Subjects> getSubjects() { return plansDao.getSubjects(); }

    public List<String> getYears() { return plansDao.getYears(); }

    public List<Teachers> getTeachers() { return plansDao.getTeachers(); }

    public Plans getPlans(int teacherId, int subjectId, int year) {
        return plansDao.getPlans(teacherId, subjectId, year);
    }

    public Plans getPlan(int planId) {
        return plansDao.getPlans(planId);
    }
}