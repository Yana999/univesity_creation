package ru.abdramanova.university_platform.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudyGroup {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Person> people;


    public StudyGroup() {
    }

    public StudyGroup(String name) {
        this.name = name;
    }

    public StudyGroup(String name, List<Person> users) {
        this.name = name;
        this.people = users;
    }

    public Integer tId() {
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

    public List<Person> getUsers() {
        return people;
    }

    public void setUsers(List<Person> users) {
        this.people = users;
    }
}