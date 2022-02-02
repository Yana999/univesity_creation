package ru.abdramanova.univesity_creation.Entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_generator"
    )
    @GenericGenerator(
            name = "user_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "3"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    private long id;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String SecondName;
    @Column(nullable = false, length = 16)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    private StudyGroup studyGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonRole personRole;
    public Person() {
    }

    public Person(String surname, String name, String secondName, String phoneNumber, String email, StudyGroup studyGroup, PersonRole personRole) {
        this.surname = surname;
        this.name = name;
        SecondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyGroup = studyGroup;
        this.personRole = personRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    public PersonRole getRole() {
        return personRole;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudyGroup getGroup() {
        return studyGroup;
    }

    public void setGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }
}
