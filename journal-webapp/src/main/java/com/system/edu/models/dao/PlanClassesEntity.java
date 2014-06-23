package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created by sph on 23.06.2014.
 */
@Entity
@Table(name = "plan_classes", schema = "", catalog = "journal")
public class PlanClassesEntity {
    private int id;
    private Integer subgroup;
    private PlansEntity plansByPlan;
    private ClassesEntity classesByClazz;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subgroup")
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

        PlanClassesEntity that = (PlanClassesEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "plan", referencedColumnName = "id", nullable = false)
    public PlansEntity getPlansByPlan() {
        return plansByPlan;
    }

    public void setPlansByPlan(PlansEntity plansByPlan) {
        this.plansByPlan = plansByPlan;
    }

    @ManyToOne
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public ClassesEntity getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(ClassesEntity classesByClazz) {
        this.classesByClazz = classesByClazz;
    }
}
