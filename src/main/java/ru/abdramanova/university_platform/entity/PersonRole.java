package ru.abdramanova.university_platform.entity;

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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
