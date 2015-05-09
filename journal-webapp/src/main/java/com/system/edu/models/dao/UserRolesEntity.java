package com.system.edu.models.dao;

import javax.persistence.*;


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

    private Role roleByRolecode;
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "rolecode", referencedColumnName = "id", nullable = false)
    public Role getRoleByRolecode() {
        return roleByRolecode;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    public UsersEntity getUser() {
        return user;
    }

    public void setRoleByRolecode(Role roleByRolecode) {
        this.roleByRolecode = roleByRolecode;
    }

    public void setUser(UsersEntity user) { this.user = user; }

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
