package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.entity.Material;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TaskWithAssessmentsDTO {

    private Long id;
    private String name;
    private List<AssessmentDTO> assessments;
}
