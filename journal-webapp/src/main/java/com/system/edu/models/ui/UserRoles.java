package com.system.edu.models.ui;

/**
 * Created by nata on 15.06.2014.
 */


public class UserRoles {
    private int id;
    private Roles rolesByRolecode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (id != userRoles.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Roles getRolesByRolecode() {
        return rolesByRolecode;
    }

    public void setRolesByRolecode(Roles rolesByRolecode) {
        this.rolesByRolecode = rolesByRolecode;
    }
}
