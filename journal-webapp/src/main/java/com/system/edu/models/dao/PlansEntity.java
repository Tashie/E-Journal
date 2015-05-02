package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: nata
 * Date: 27.06.14
 */
@Table(name = "plans", schema = "", catalog = "journal")
@Entity
public class PlansEntity {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GenericGenerator(name="plId" , strategy="increment")
    @GeneratedValue(generator="plId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int year;

    @Column(name = "year", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
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

    private Collection<PlanClassesEntity> planClassesesById;

    @OneToMany(mappedBy = "plansByPlan")
    public Collection<PlanClassesEntity> getPlanClassesesById() {
        return planClassesesById;
    }

    public void setPlanClassesesById(Collection<PlanClassesEntity> planClassesesById) {
        this.planClassesesById = planClassesesById;
    }

    private Collection<PlanLessonsEntity> planLessonsesById;

    @OneToMany(mappedBy = "plansByPlan")
    public Collection<PlanLessonsEntity> getPlanLessonsesById() {
        return planLessonsesById;
    }

    public void setPlanLessonsesById(Collection<PlanLessonsEntity> planLessonsesById) {
        this.planLessonsesById = planLessonsesById;
    }

    private TeachersEntity teachersByTeacher;

    @ManyToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id", nullable = false)
    public TeachersEntity getTeachersByTeacher() {
        return teachersByTeacher;
    }

    public void setTeachersByTeacher(TeachersEntity teachersByTeacher) {
        this.teachersByTeacher = teachersByTeacher;
    }

    private SubjectsEntity subjectsBySubject;

    @ManyToOne
    @JoinColumn(name = "subject", referencedColumnName = "id", nullable = false)
    public SubjectsEntity getSubjectsBySubject() {
        return subjectsBySubject;
    }

    public void setSubjectsBySubject(SubjectsEntity subjectsBySubject) {
        this.subjectsBySubject = subjectsBySubject;
    }

    private int classFor;

    @Column(name = "class", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getClassFor() {
        return classFor;
    }

    public void setClassFor(int classFor) {
        this.classFor = classFor;
    }

    private String notes;

    @Column(name = "notes", nullable = true, insertable = true, updatable = true)
    @Basic
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}