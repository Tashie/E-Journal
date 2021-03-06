package com.system.edu.models.ui;

import java.sql.Date;

/**
 * Created by nata on 15.06.2014.
 */

public class PlanClasses {
    private int id;
    private Integer subgroup;
    private Plans plansByPlan;
    private Classes classesByClazz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Integer subgroup) {
        this.subgroup = subgroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanClasses that = (PlanClasses) o;

        if (id != that.id) return false;
        if (subgroup != null ? !subgroup.equals(that.subgroup) : that.subgroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subgroup != null ? subgroup.hashCode() : 0);
        return result;
    }

    public Plans getPlansByPlan() {
        return plansByPlan;
    }

    public void setPlansByPlan(Plans plansByPlan) {
        this.plansByPlan = plansByPlan;
    }

    public Classes getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(Classes classesByClazz) {
        this.classesByClazz = classesByClazz;
    }
}