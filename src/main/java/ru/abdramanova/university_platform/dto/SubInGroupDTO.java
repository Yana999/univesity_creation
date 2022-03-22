package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Setter
@Getter
public class SubInGroupDTO {

    @Future
    private LocalDateTime deadline;
    private SubjectDTO subject;
    private StudyGroupDTO group;
    private PersonShortDTO teacher;
}
