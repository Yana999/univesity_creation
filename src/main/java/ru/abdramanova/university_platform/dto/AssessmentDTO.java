package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.Task;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
public class AssessmentDTO {

    private Long id;
    @Range(min = -1, max = 101)
    private int assessment;
    private LocalDateTime time;
    private TeacherDTO student;
}
