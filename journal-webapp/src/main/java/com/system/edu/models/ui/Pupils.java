package com.system.edu.models.ui;

import java.sql.Date;

/**
 * Created by nata on 15.06.2014.
 */

public class Pupils {
    private int id;
    private String lastname;
    private String firstname;
    private String middlename;
    private Date birthdate;
    private String address;
    private Classes classesByClazz;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

        Pupils pupils = (Pupils) o;

        if (id != pupils.id) return false;
        if (address != null ? !address.equals(pupils.address) : pupils.address != null) return false;
        if (birthdate != null ? !birthdate.equals(pupils.birthdate) : pupils.birthdate != null) return false;
        if (firstname != null ? !firstname.equals(pupils.firstname) : pupils.firstname != null) return false;
        if (lastname != null ? !lastname.equals(pupils.lastname) : pupils.lastname != null) return false;
        if (middlename != null ? !middlename.equals(pupils.middlename) : pupils.middlename != null) return false;

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


    public Classes getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(Classes classesByClazz) {
        this.classesByClazz = classesByClazz;
    }
}
