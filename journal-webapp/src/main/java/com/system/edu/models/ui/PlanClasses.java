package com.system.edu.models.ui;

import java.sql.Date;

/**
 * Created by nata on 15.06.2014.
 */


public class PlanClasses {
    private int id;
    private Integer subgroup;
    private Date pcDate;
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


    public Date getPcDate() {
        return pcDate;
    }

    public void setPcDate(Date pcDate) {
        this.pcDate = pcDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanClasses that = (PlanClasses) o;

        if (id != that.id) return false;
        if (pcDate != null ? !pcDate.equals(that.pcDate) : that.pcDate != null) return false;
        if (subgroup != null ? !subgroup.equals(that.subgroup) : that.subgroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subgroup != null ? subgroup.hashCode() : 0);
        result = 31 * result + (pcDate != null ? pcDate.hashCode() : 0);
        return result;
    }


    public Classes getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(Classes classesByClazz) {
        this.classesByClazz = classesByClazz;
    }
}
