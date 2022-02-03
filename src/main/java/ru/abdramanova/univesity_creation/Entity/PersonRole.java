package ru.abdramanova.univesity_creation.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "Students_platform")
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
    private long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "personRole", orphanRemoval = true)
    private List<Person> people;

    public PersonRole() {
    }

    public PersonRole(String name, List<Person> people) {
        this.name = name;
        this.people = people;
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
