package com.system.edu.models.ui;

/**
 * Created by nata on 15.06.2014.
 */

public class Users {
    private int id;
    private String username;
    private String password;
    private byte enabled;
    private String firstname;
    private String lastname;
    private String middlename;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
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

        Users users = (Users) o;

        if (enabled != users.enabled) return false;
        if (id != users.id) return false;
        if (address != null ? !address.equals(users.address) : users.address != null) return false;
        if (firstname != null ? !firstname.equals(users.firstname) : users.firstname != null) return false;
        if (lastname != null ? !lastname.equals(users.lastname) : users.lastname != null) return false;
        if (middlename != null ? !middlename.equals(users.middlename) : users.middlename != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (username != null ? !username.equals(users.username) : users.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) enabled;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
