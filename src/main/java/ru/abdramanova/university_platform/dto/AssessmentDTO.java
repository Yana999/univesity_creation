package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Setter
@Getter
public class AssessmentDTO {

    private Long id;
    @Range(min = -1, max = 101)
    private int assessment;
    private PersonShortDTO student;
}
