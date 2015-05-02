package com.system.edu.models.ui;

import com.google.common.base.Joiner;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by nata on 15.06.2014.
 */
public class Teachers {
    private int id;
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;
    @NotNull
    @Size(min = 4, max = 50)
    private String middlename;
    private Date birthdate;
    @NotNull
    @Size(min = 4, max = 100)
    private String address;
    private Positions positionsByPosition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthdate() { return birthdate; }

    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getFIO() {
        return Joiner.on(" ").join(Arrays.asList(middlename, firstname, lastname));
    }

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

    public Positions getPositionsByPosition() {
        return positionsByPosition;
    }

    public void setPositionsByPosition(Positions positionsByPosition) {
        this.positionsByPosition = positionsByPosition;
    }
}
