package ru.abdramanova.university_platform.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Embeddable
public class TaskKey implements Serializable {

    private Timestamp id;
    private String name;

    public TaskKey() {
    }

    public TaskKey(String name) {
        this.id = new Timestamp( System.currentTimeMillis() );
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

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
