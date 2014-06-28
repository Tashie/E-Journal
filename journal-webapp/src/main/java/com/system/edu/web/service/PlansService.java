package com.system.edu.web.service;

import com.system.edu.models.dao.PlansEntity;
import com.system.edu.models.dao.SubjectsEntity;
import com.system.edu.models.ui.Plans;
import com.system.edu.models.ui.Subjects;
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
}
