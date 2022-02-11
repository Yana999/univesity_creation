package ru.abdramanova.university_platform.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
public class Person extends Human{

    @Column(nullable = false, length = 16)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    private StudyGroup studyGroup;
    @ManyToOne
    private PersonRole personRole;
    public Person() {
    }

    public Person(String surname, String name, String secondName, String phoneNumber, String email, PersonRole personRole) {
        super(surname, name, secondName);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.personRole = personRole;
    }

    public Person(String surname, String name, String secondName, String phoneNumber, String email, StudyGroup studyGroup, PersonRole personRole) {
        super(surname, name, secondName);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyGroup = studyGroup;
        this.personRole = personRole;
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

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + super.getId() +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
