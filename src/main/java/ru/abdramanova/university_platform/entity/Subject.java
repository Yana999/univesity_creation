package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Subject {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int subjectId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private ControlFormDict controlForm;

    @OneToMany( mappedBy = "subject")
    private List<SubInGroup> subjectsInfo;

    public Subject(String name, ControlFormDict controlForm) {
        this.name = name;
        this.controlForm = controlForm;
    }

}
