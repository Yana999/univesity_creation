package ru.abdramanova.univesity_creation.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudyGroup {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;
    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY)
    private List<Person> users;


    public StudyGroup() {
    }

    public StudyGroup(String name, List<Person> users) {
        this.name = name;
        this.users = users;
    }

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

    public List<Person> getUsers() {
        return users;
    }

    public void setUsers(List<Person> users) {
        this.users = users;
    }
}
