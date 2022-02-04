package ru.abdramanova.university_platform.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ControlFormDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NaturalId
    private String name;

    public ControlFormDict() {
    }

    public ControlFormDict(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
