package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created by sph on 23.06.2014.
 */
@Entity
@Table(name = "plans", schema = "", catalog = "journal")
public class PlansEntity {
    private int id;
    private int year;
    private TeachersEntity teachersByTeacher;
    private SubjectsEntity subjectsBySubject;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlansEntity that = (PlansEntity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + year;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id", nullable = false)
    public TeachersEntity getTeachersByTeacher() {
        return teachersByTeacher;
    }

    public void setTeachersByTeacher(TeachersEntity teachersByTeacher) {
        this.teachersByTeacher = teachersByTeacher;
    }

    @ManyToOne
    @JoinColumn(name = "subject", referencedColumnName = "id", nullable = false)
    public SubjectsEntity getSubjectsBySubject() {
        return subjectsBySubject;
    }

    public void setSubjectsBySubject(SubjectsEntity subjectsBySubject) {
        this.subjectsBySubject = subjectsBySubject;
    }
}
