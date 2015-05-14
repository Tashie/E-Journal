package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Table(name = "classes", schema = "", catalog = "journal")
@Entity
public class Classes {
    private int id;
    @NotNull
    @Size(min = 4, max = 150)
    private String name;
    private Teacher teachersByHeadTeacher;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GenericGenerator(name="classId" , strategy="increment")
    @GeneratedValue(generator="classId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "head_teacher", referencedColumnName = "id", nullable = false)
    public Teacher getTeachersByHeadTeacher() {
        return teachersByHeadTeacher;
    }


    public void setTeachersByHeadTeacher(Teacher teachersByHeadTeacher) {
        this.teachersByHeadTeacher = teachersByHeadTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes that = (Classes) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
