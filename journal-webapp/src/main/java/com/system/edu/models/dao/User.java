package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Table(name = "users", schema = "", catalog = "journal")
@Entity
public class User {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GenericGenerator(name="userId" , strategy="increment")
    @GeneratedValue(generator="userId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String uuid;

    @Column(name = "uuid", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    private String address;

    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Byte enabled;

    @Column(name = "enabled", nullable = true, insertable = true, updatable = true, length = 3, precision = 0)
    @Basic
    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    private String firstname;

    @Column(name = "firstname", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    @Column(name = "lastname", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String middlename;

    @Column(name = "middlename", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    private String password;

    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
