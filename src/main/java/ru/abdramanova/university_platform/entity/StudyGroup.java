package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class StudyGroup {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(nullable = false)
    @NotNull
    private String name;
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "studyGroup")
    private List<Person> people;
    @OneToMany
    private List<SubInGroup> subInGroups;


    public StudyGroup() {
    }

    public StudyGroup(String name) {
        this.name = name;
    }

    public StudyGroup(String name, List<Person> users) {
        this.name = name;
        this.people = users;
    }

    public StudyGroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @JsonIgnore
    public List<SubInGroup> getSubInGroups() {
        return subInGroups;
    }

    public void setSubInGroups(List<SubInGroup> subInGroups) {
        this.subInGroups = subInGroups;
    }

    @JsonIgnore
    public List<Person> getUsers() {
        return people;
    }

    public void setUsers(List<Person> users) {
        this.people = users;
    }
}
