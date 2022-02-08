package ru.abdramanova.university_platform.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ControlFormDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NaturalId
    private String name;
    @OneToMany(mappedBy = "controlForm", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Subject> subjects = new ArrayList<>();

    public ControlFormDict() {
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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
