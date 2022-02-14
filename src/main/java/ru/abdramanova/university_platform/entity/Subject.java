package ru.abdramanova.university_platform.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private ControlFormDict controlForm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<SubInGroup> subjectsInfo;

    public Subject() {
    }

    public Subject(String name, ControlFormDict controlForm) {
        this.name = name;
        this.controlForm = controlForm;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ControlFormDict getControlForm() {
        return controlForm;
    }

    public void setControlForm(ControlFormDict controlForm) {
        this.controlForm = controlForm;
    }

    public List<SubInGroup> getSubjectsInfo() {
        return subjectsInfo;
    }

    public void setSubjectsInfo(List<SubInGroup> subjectsInfo) {
        this.subjectsInfo = subjectsInfo;
    }
}
