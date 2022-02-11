package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class PersonRole {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-gen"
    )
    @SequenceGenerator(
            name = "sequence-gen",
            sequenceName = "role_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "personRole", orphanRemoval = true)
    private List<Person> people;

    public PersonRole() {
    }

    public PersonRole(Long id) {
        this.id = id;
    }

    public PersonRole(String name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "PersonRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", people=" + people +
                '}';
    }
}
