package ru.abdramanova.univesity_creation.Entity;

import javax.persistence.*;

@Entity
public class Material {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Lob
    private byte[] file;
    @ManyToOne
    private Task task;

    public Material() {
    }

    public Material(String name, byte[] file, Task task) {
        this.name = name;
        this.file = file;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }


}
