package com.system.edu.models.dao;

import javax.persistence.*;


@Table(name = "grades", schema = "", catalog = "journal")
@Entity
public class Grades {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int grade;

    @Column(name = "grade", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private Integer planClassesId;

    @Column(name = "plan_classes_id", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getPlanClassesId() {
        return planClassesId;
    }

    public void setPlanClassesId(Integer planClassesId) {
        this.planClassesId = planClassesId;
    }


    private GradeTypes gradeTypesByType;


    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id", nullable = false)
    public GradeTypes getGradeTypesByType() {
        return gradeTypesByType;
    }

    public void setGradeTypesByType(GradeTypes gradeTypesByType) {
        this.gradeTypesByType = gradeTypesByType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grades that = (Grades) o;

        if (grade != that.grade) return false;
        if (id != that.id) return false;
        if (planClassesId != null ? !planClassesId.equals(that.planClassesId) : that.planClassesId != null)
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
}
