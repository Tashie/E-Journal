package com.system.edu.models.dao;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by sph on 15.06.2014.
 */
@Entity
@Table(name = "plan_classes", schema = "", catalog = "journal")
public class PlanClasses {
    private int id;
    private Integer subgroup;
    private Date pcDate;
    private Classes classesByClazz;

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

    @Basic
    @Column(name = "pc_date")
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

    @ManyToOne
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public Classes getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(Classes classesByClazz) {
        this.classesByClazz = classesByClazz;
    }
}
