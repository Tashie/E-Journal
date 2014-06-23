package com.system.edu.models.dao;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by sph on 23.06.2014.
 */
@Entity
@Table(name = "schedule", schema = "", catalog = "journal")
public class ScheduleEntity {
    private int id;
    private Date lessonDate;
    private PlanLessonsEntity planLessonsByPlanLessons;
    private PlanClassesEntity planClassesByPlanClasses;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lesson_date")
    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (id != that.id) return false;
        if (lessonDate != null ? !lessonDate.equals(that.lessonDate) : that.lessonDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lessonDate != null ? lessonDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "plan_lessons", referencedColumnName = "id", nullable = false)
    public PlanLessonsEntity getPlanLessonsByPlanLessons() {
        return planLessonsByPlanLessons;
    }

    public void setPlanLessonsByPlanLessons(PlanLessonsEntity planLessonsByPlanLessons) {
        this.planLessonsByPlanLessons = planLessonsByPlanLessons;
    }

    @ManyToOne
    @JoinColumn(name = "plan_classes", referencedColumnName = "id", nullable = false)
    public PlanClassesEntity getPlanClassesByPlanClasses() {
        return planClassesByPlanClasses;
    }

    public void setPlanClassesByPlanClasses(PlanClassesEntity planClassesByPlanClasses) {
        this.planClassesByPlanClasses = planClassesByPlanClasses;
    }
}