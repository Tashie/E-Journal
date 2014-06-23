package com.system.edu.models.ui;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by nata on 15.06.2014.
 */

public class Positions {
    private int id;

    @NotNull
    @Size(min = 4, max = 150)
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Positions positions = (Positions) o;

        if (id != positions.id) return false;
        if (name != null ? !name.equals(positions.name) : positions.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
