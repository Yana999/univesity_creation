package ru.abdramanova.university_platform.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Material {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private String contentType;
    private Long size;
    @Lob
    private byte[] file;
    @ManyToOne
    private Task task;

    public Material() {
    }


    public Material(String name, String contentType, Long size, @NotNull byte[] file, Task task) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.file = file;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
