package com.system.edu.models.dao;

import javax.persistence.*;
import java.util.Collection;


@javax.persistence.Table(name = "plan_classes", schema = "", catalog = "journal")
@Entity
public class PlanClass {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int subgroup;

    @javax.persistence.Column(name = "subgroup", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(int subgroup) {
        this.subgroup = subgroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanClass that = (PlanClass) o;

        if (id != that.id) return false;
        if (subgroup != that.subgroup) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subgroup;
        return result;
    }

    private Plan plansByPlan;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "plan", referencedColumnName = "id", nullable = false)
    public Plan getPlansByPlan() {
        return plansByPlan;
    }

    public void setPlansByPlan(Plan plansByPlan) {
        this.plansByPlan = plansByPlan;
    }

    private Classes classesByClazz;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public Classes getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(Classes classesByClazz) {
        this.classesByClazz = classesByClazz;
    }

    private Collection<ScheduleEntity> schedulesById;

    @OneToMany(mappedBy = "planClassesByPlanClasses")
    public Collection<ScheduleEntity> getSchedulesById() {
        return schedulesById;
    }

    public void setSchedulesById(Collection<ScheduleEntity> schedulesById) {
        this.schedulesById = schedulesById;
    }
}
