package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import ru.abdramanova.university_platform.entity.Task;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class MaterialDTO {

    private Long id;
    private String name;
    private byte[] file;
    private String contentType;

}
