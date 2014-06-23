package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created by sph on 23.06.2014.
 */
@Entity
@Table(name = "plan_lessons", schema = "", catalog = "journal")
public class PlanLessonsEntity {
    private int id;
    private int lesson;
    private String topic;
    private PlansEntity plansByPlan;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lesson")
    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    @Basic
    @Column(name = "topic")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanLessonsEntity that = (PlanLessonsEntity) o;

        if (id != that.id) return false;
        if (lesson != that.lesson) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + lesson;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "plan", referencedColumnName = "id", nullable = false)
    public PlansEntity getPlansByPlan() {
        return plansByPlan;
    }

    public void setPlansByPlan(PlansEntity plansByPlan) {
        this.plansByPlan = plansByPlan;
    }
}