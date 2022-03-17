package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ControlFormDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NaturalId
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "controlForm", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Subject> subjects = new ArrayList<>();

    public ControlFormDict(String name) {
        this.name = name;
    }
}
