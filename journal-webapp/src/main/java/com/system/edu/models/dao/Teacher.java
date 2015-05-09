package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;



@Table(name = "teachers", schema = "", catalog = "journal")
@Entity
public class Teacher {
    private int id;
    private String lastname;
    private String firstname;
    private String middlename;
    private Date birthdate;
    private String address;
    private Positions positionsByPositions;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GenericGenerator(name="teachId" , strategy="increment")
    @GeneratedValue(generator="teachId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "lastname", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "firstname", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "middlename", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Column(name = "birthdate", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Column(name = "address", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "position", referencedColumnName = "id", nullable = false)
    public Positions getPositionsByPositions() {
        return positionsByPositions;
    }

    public void setPositionsByPositions(Positions positionsByPositions) {
        this.positionsByPositions = positionsByPositions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher that = (Teacher) o;

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
