package com.system.edu.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by sph on 15.06.2014.
 */
@Entity
public class Teachers {
    private int id;
    private String lastname;
    private String firstname;
    private String middlename;
    private Date birthdate;
    private String address;
    private Positions positionsByPosition;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "middlename")
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teachers teachers = (Teachers) o;

        if (id != teachers.id) return false;
        if (address != null ? !address.equals(teachers.address) : teachers.address != null) return false;
        if (birthdate != null ? !birthdate.equals(teachers.birthdate) : teachers.birthdate != null) return false;
        if (firstname != null ? !firstname.equals(teachers.firstname) : teachers.firstname != null) return false;
        if (lastname != null ? !lastname.equals(teachers.lastname) : teachers.lastname != null) return false;
        if (middlename != null ? !middlename.equals(teachers.middlename) : teachers.middlename != null) return false;

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

    @ManyToOne
    @JoinColumn(name = "position", referencedColumnName = "id", nullable = false)
    public Positions getPositionsByPosition() {
        return positionsByPosition;
    }

    public void setPositionsByPosition(Positions positionsByPosition) {
        this.positionsByPosition = positionsByPosition;
    }
}
