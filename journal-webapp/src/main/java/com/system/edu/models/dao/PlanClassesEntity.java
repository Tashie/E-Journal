package com.system.edu.models.dao;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 17.06.14
 * Time: 9:00
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "plan_classes", schema = "", catalog = "journal")
@Entity
public class PlanClassesEntity {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Integer subgroup;

    @Column(name = "subgroup", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Integer subgroup) {
        this.subgroup = subgroup;
    }

    private Date pcDate;

    @Column(name = "pc_date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getPcDate() {
        return pcDate;
    }

    public void setPcDate(Date pcDate) {
        this.pcDate = pcDate;
    }

    private ClassesEntity classesByClazz;

    @ManyToOne
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public ClassesEntity getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(ClassesEntity classesByClazz) {
        this.classesByClazz = classesByClazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanClassesEntity that = (PlanClassesEntity) o;

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
}
