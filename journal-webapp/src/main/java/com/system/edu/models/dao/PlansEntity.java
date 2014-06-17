package com.system.edu.models.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 17.06.14
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "plans", schema = "", catalog = "journal")
@Entity
public class PlansEntity {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int lesson;

    @Column(name = "lesson", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    private String topic;

    @Column(name = "topic", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
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

        PlansEntity that = (PlansEntity) o;

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
}
