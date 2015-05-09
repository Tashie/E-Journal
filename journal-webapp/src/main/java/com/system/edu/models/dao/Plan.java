package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;


@Table(name = "plans", schema = "", catalog = "journal")
@Entity
public class Plan {
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

        Plan that = (Plan) o;

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

    private Collection<PlanClass> planClassesesById;

    @OneToMany(mappedBy = "plansByPlan")
    public Collection<PlanClass> getPlanClassesesById() {
        return planClassesesById;
    }

    public void setPlanClassesesById(Collection<PlanClass> planClassesesById) {
        this.planClassesesById = planClassesesById;
    }

    private Collection<PlanLesson> planLessonsesById;

    @OneToMany(mappedBy = "plansByPlan")
    public Collection<PlanLesson> getPlanLessonsesById() {
        return planLessonsesById;
    }

    public void setPlanLessonsesById(Collection<PlanLesson> planLessonsesById) {
        this.planLessonsesById = planLessonsesById;
    }

    private Teacher teachersByTeacher;

    @ManyToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id", nullable = false)
    public Teacher getTeachersByTeacher() {
        return teachersByTeacher;
    }

    public void setTeachersByTeacher(Teacher teachersByTeacher) {
        this.teachersByTeacher = teachersByTeacher;
    }

    private Subject subjectsBySubject;

    @ManyToOne
    @JoinColumn(name = "subject", referencedColumnName = "id", nullable = false)
    public Subject getSubjectsBySubject() {
        return subjectsBySubject;
    }

    public void setSubjectsBySubject(Subject subjectsBySubject) {
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