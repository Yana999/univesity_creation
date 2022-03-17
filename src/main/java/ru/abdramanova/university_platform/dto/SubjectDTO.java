package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import ru.abdramanova.university_platform.entity.ControlFormDict;
import ru.abdramanova.university_platform.entity.SubInGroup;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SubjectDTO {

    private Integer subjectId;
    @NotBlank
    private String name;
    private ControlFormDTO controlForm;
}
