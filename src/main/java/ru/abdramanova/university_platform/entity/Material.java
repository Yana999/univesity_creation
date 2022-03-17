package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "invoice")
@EqualsAndHashCode(exclude = "invoice")
public class Material {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private String contentType;
    @Lob
    private byte[] file;
    @ManyToOne
    private Task task;
    @Transient
    private String url;
    @Transient
    private int size;

    public Material(String name, String contentType, Long size, @NotNull byte[] file, Task task) {
        this.name = name;
        this.contentType = contentType;
        this.file = file;
        this.task = task;
    }

    public void setFile(byte[] file) {
        this.file = file;
        this.size = file.length;
        System.out.println(file.length);
    }

    public void setId(Long id) {
        this.id = id;
        this.url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/task/material/")
                .path(id.toString())
                .toUriString();
    }

    public String getUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/task/material/")
                .path(id.toString())
                .toUriString();
    }

    public int getSize() {
        return file.length;
    }
}
