package ru.abdramanova.univesity_creation.Entity;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Table(schema = "Students_platform")
public class Subject {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private ControlFormDict controlForm;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubInGroup subjectInfo;

    public Subject() {
    }

    public Subject(String name, ControlFormDict controlForm, SubInGroup subjectInfo) {
        this.name = name;
        this.controlForm = controlForm;
        this.subjectInfo = subjectInfo;
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


}
