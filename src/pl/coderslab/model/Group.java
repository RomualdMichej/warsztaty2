package pl.coderslab.model;

import java.util.Date;

public class Group {
    private int id;
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
