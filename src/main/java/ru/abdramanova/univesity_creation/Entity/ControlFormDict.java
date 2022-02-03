package ru.abdramanova.univesity_creation.Entity;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "Students_platform")
public class ControlFormDict {

    @Id
    private short id;
    @NaturalId
    private String name;

    public ControlFormDict() {
    }

    public ControlFormDict(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
