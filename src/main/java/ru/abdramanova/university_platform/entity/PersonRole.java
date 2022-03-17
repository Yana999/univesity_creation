package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
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
    @NotNull
    private String name;
    @OneToMany(mappedBy = "personRole", orphanRemoval = true)
    private List<Person> people;

    @Override
    public String toString() {
        return "PersonRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", people=" + people +
                '}';
    }

    public PersonRole(String name) {
        this.name = name;
    }
}
