package com.system.edu.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sph on 15.06.2014.
 */
@Entity
public class Plans {
    private int id;
    private int lesson;
    private String topic;

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
