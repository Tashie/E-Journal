package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Table(name = "subjects", schema = "", catalog = "journal")
@Entity
public class Subject {
    private int id;
    private String name;
    private String nameShorten;
    private int difficulty;
    private Cycle cycleByCycle;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GenericGenerator(name="subjectId" , strategy="increment")
    @GeneratedValue(generator="subjectId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name_shorten", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getNameShorten() {
        return nameShorten;
    }

    public void setNameShorten(String nameShorten) {
        this.nameShorten = nameShorten;
    }

    @Column(name = "difficulty", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @ManyToOne
    @JoinColumn(name = "cycle", referencedColumnName = "id", nullable = false)
    public Cycle getCycleByCycle() {
        return cycleByCycle;
    }

    public void setCycleByCycle(Cycle cycleByCycle) {
        this.cycleByCycle = cycleByCycle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject that = (Subject) o;

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
