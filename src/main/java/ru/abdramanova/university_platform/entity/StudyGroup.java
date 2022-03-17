package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudyGroup {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer groupId;
    @Column(nullable = false)
    @NotNull
    private String name;
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "studyGroup")
    private List<Person> people;
    @OneToMany
    private List<SubInGroup> subInGroups;

    public StudyGroup(String name) {
        this.name = name;
    }

}
