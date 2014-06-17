package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 17.06.14
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "subjects", schema = "", catalog = "journal")
@Entity
public class SubjectsEntity {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String nameShorten;

    @Column(name = "name_shorten", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getNameShorten() {
        return nameShorten;
    }

    public void setNameShorten(String nameShorten) {
        this.nameShorten = nameShorten;
    }

    private int difficulty;

    @Column(name = "difficulty", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    private CyclesEntity cyclesByCycle;

    @ManyToOne
    @JoinColumn(name = "cycle", referencedColumnName = "id", nullable = false)
    public CyclesEntity getCyclesByCycle() {
        return cyclesByCycle;
    }

    public void setCyclesByCycle(CyclesEntity cyclesByCycle) {
        this.cyclesByCycle = cyclesByCycle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectsEntity that = (SubjectsEntity) o;

        if (difficulty != that.difficulty) return false;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nameShorten != null ? !nameShorten.equals(that.nameShorten) : that.nameShorten != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameShorten != null ? nameShorten.hashCode() : 0);
        result = 31 * result + difficulty;
        return result;
    }
}
