package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created by sph on 15.06.2014.
 */
@Entity
public class Grades {
    private int id;
    private int grade;
    private Integer planClassesId;
    private GradeTypes gradeTypesByType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "grade")
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "plan_classes_id")
    public Integer getPlanClassesId() {
        return planClassesId;
    }

    public void setPlanClassesId(Integer planClassesId) {
        this.planClassesId = planClassesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grades grades = (Grades) o;

        if (grade != grades.grade) return false;
        if (id != grades.id) return false;
        if (planClassesId != null ? !planClassesId.equals(grades.planClassesId) : grades.planClassesId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + grade;
        result = 31 * result + (planClassesId != null ? planClassesId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id", nullable = false)
    public GradeTypes getGradeTypesByType() {
        return gradeTypesByType;
    }

    public void setGradeTypesByType(GradeTypes gradeTypesByType) {
        this.gradeTypesByType = gradeTypesByType;
    }
}
