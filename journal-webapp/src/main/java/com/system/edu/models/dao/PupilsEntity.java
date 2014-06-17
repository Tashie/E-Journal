package com.system.edu.models.dao;

import com.system.edu.models.ui.Classes;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 17.06.14
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "pupils", schema = "", catalog = "journal")
@Entity
public class PupilsEntity {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String lastname;

    @Column(name = "lastname", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String firstname;

    @Column(name = "firstname", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String middlename;

    @Column(name = "middlename", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    private Date birthdate;

    @Column(name = "birthdate", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    private String address;

    @Column(name = "address", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private ClassesEntity classesByClazz;

    @ManyToOne
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public ClassesEntity getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(ClassesEntity classesByClazz) {
        this.classesByClazz = classesByClazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PupilsEntity that = (PupilsEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}
