package com.system.edu.models.dao;

import javax.persistence.*;
import java.util.Collection;



@SuppressWarnings("ALL")
@javax.persistence.Table(name = "plan_lessons", schema = "", catalog = "journal")
@Entity
public class PlanLesson {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int lesson;

    @javax.persistence.Column(name = "lesson", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    private String topic;

    @javax.persistence.Column(name = "topic", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
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

        PlanLesson that = (PlanLesson) o;

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

    private Plan plansByPlan;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "plan", referencedColumnName = "id", nullable = false)
    public Plan getPlansByPlan() {
        return plansByPlan;
    }

    public void setPlansByPlan(Plan plansByPlan) {
        this.plansByPlan = plansByPlan;
    }

    private Collection<ScheduleEntity> schedulesById;

    @OneToMany(mappedBy = "planLessonsByPlanLessons")
    public Collection<ScheduleEntity> getSchedulesById() {
        return schedulesById;
    }

    public void setSchedulesById(Collection<ScheduleEntity> schedulesById) {
        this.schedulesById = schedulesById;
    }
}
