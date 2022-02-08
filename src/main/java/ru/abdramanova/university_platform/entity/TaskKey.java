package ru.abdramanova.university_platform.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskKey implements Serializable {
    private long id;
    private String name;

    public TaskKey() {
    }

    public TaskKey(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskKey personKey = (TaskKey) o;
        return id == personKey.id && name.equals(personKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String surname) {
        this.name = surname;
    }
}
