package ru.abdramanova.university_platform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Person extends Human implements UserDetails {

    @Column(nullable = false, length = 11)
    @Digits(integer = 11, fraction = 0)
    private String phoneNumber;
    @Column(nullable = false)
    @Email
    @NotBlank(message = "email is required")
    private String email;
    @ManyToOne
    private StudyGroup studyGroup;
    @ManyToOne
    private PersonRole personRole;

    public Person(String login, String password, String surname, String name, String secondName, String phoneNumber, String email, PersonRole personRole) {
        super(surname, name, secondName, password, login);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.personRole = personRole;
    }

    public Person(String login, String password, String surname, String name, String secondName, String phoneNumber, String email, StudyGroup studyGroup, PersonRole personRole) {
        super(surname, name, secondName, password, login);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyGroup = studyGroup;
        this.personRole = personRole;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public StudyGroup getStudyGroup() {
//        return studyGroup;
//    }
//
//    public void setStudyGroup(StudyGroup studyGroup) {
//        this.studyGroup = studyGroup;
//    }
//
//    public PersonRole getPersonRole() {
//        return personRole;
//    }
//
//    public void setPersonRole(PersonRole personRole) {
//        this.personRole = personRole;
//    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(personRole.getName()));
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
