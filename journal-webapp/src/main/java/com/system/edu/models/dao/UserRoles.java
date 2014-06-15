package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created by sph on 15.06.2014.
 */
@Entity
@Table(name = "user_roles", schema = "", catalog = "journal")
public class UserRoles {
    private int id;
    private Roles rolesByRolecode;

    @Id
    @Column(name = "id")
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

    @ManyToOne
    @JoinColumn(name = "rolecode", referencedColumnName = "id", nullable = false)
    public Roles getRolesByRolecode() {
        return rolesByRolecode;
    }

    public void setRolesByRolecode(Roles rolesByRolecode) {
        this.rolesByRolecode = rolesByRolecode;
    }
}
