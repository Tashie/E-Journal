package com.system.edu.models.ui;

/**
 * Created by nata on 15.06.2014.
 */

public class Subjects {
    private int id;
    private String name;
    private String nameShorten;
    private int difficulty;
    private Cycles cyclesByCycle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameShorten() {
        return nameShorten;
    }

    public void setNameShorten(String nameShorten) {
        this.nameShorten = nameShorten;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subjects subjects = (Subjects) o;

        if (difficulty != subjects.difficulty) return false;
        if (id != subjects.id) return false;
        if (name != null ? !name.equals(subjects.name) : subjects.name != null) return false;
        if (nameShorten != null ? !nameShorten.equals(subjects.nameShorten) : subjects.nameShorten != null)
            return false;

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

    public Cycles getCyclesByCycle() {
        return cyclesByCycle;
    }

    public void setCyclesByCycle(Cycles cyclesByCycle) {
        this.cyclesByCycle = cyclesByCycle;
    }
}
