package com.system.edu.models.dao;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: nata
 * Date: 17.06.14
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "user_roles", schema = "", catalog = "journal")
@Entity
public class UserRolesEntity {
    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private RolesEntity rolesByRolecode;

    @ManyToOne
    @JoinColumn(name = "rolecode", referencedColumnName = "id", nullable = false)
    public RolesEntity getRolesByRolecode() {
        return rolesByRolecode;
    }

    public void setRolesByRolecode(RolesEntity rolesByRolecode) {
        this.rolesByRolecode = rolesByRolecode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRolesEntity that = (UserRolesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
