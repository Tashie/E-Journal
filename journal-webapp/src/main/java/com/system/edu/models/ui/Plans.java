package com.system.edu.models.ui;

/**
 * Created by nata on 15.06.2014.
 */
public class Plans {
    private int id;
    private int lesson;
    private String topic;

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

        Plans plans = (Plans) o;

        if (id != plans.id) return false;
        if (lesson != plans.lesson) return false;
        if (topic != null ? !topic.equals(plans.topic) : plans.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + lesson;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }
}
