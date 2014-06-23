package com.system.edu.models.ui;

import java.util.Date;

/**
 * Created by sph on 23.06.2014.
 */
public class Schedule {
    private int id;
    private Date lessonDate;
    private PlanLessons planLessonsByPlanLessons;
    private PlanClasses planClassesByPlanClasses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        Schedule that = (Schedule) o;

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

    public PlanLessons getPlanLessonsByPlanLessons() {
        return planLessonsByPlanLessons;
    }

    public void setPlanLessonsByPlanLessons(PlanLessons planLessonsByPlanLessons) {
        this.planLessonsByPlanLessons = planLessonsByPlanLessons;
    }

    public PlanClasses getPlanClassesByPlanClasses() {
        return planClassesByPlanClasses;
    }

    public void setPlanClassesByPlanClasses(PlanClasses planClassesByPlanClasses) {
        this.planClassesByPlanClasses = planClassesByPlanClasses;
    }
}