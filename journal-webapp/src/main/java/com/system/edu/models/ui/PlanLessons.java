package com.system.edu.models.ui;

/**
 * Created by sph on 23.06.2014.
 */
public class PlanLessons {
    private int id;
    private int lesson;
    private String topic;
    private Plans plansByPlan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

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

        PlanLessons that = (PlanLessons) o;

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

    public Plans getPlansByPlan() {
        return plansByPlan;
    }

    public void setPlansByPlan(Plans plansByPlan) {
        this.plansByPlan = plansByPlan;
    }
}