package com.system.edu.models.ui;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by nata on 15.06.2014.
 */
public class Plans {
    private int id;
    private int year;
    private Teachers teachersByTeacher;
    private Subjects subjectsBySubject;
    private int classFor;
    private String notes;

    public Teachers getTeachersByTeacher() {
        return teachersByTeacher;
    }

    public void setTeachersByTeacher(Teachers teachersByTeacher) {
        this.teachersByTeacher = teachersByTeacher;
    }

    public Subjects getSubjectsBySubject() {
        return subjectsBySubject;
    }

    public void setSubjectsBySubject(Subjects subjectsBySubject) {
        this.subjectsBySubject = subjectsBySubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getClassFor() {
        return classFor;
    }

    public void setClassFor(int classFor) {
        this.classFor = classFor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plans that = (Plans) o;

        if (id != that.id) return false;
        if (year != that.year) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + year;
        return result;
    }
}