package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.abdramanova.university_platform.entity.Subject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ControlFormDTO {

    private short id;
    @NotBlank
    private String name;

}
