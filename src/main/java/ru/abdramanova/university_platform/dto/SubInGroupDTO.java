package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.StudyGroup;
import ru.abdramanova.university_platform.entity.Subject;
import ru.abdramanova.university_platform.entity.Task;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class SubInGroupDTO {

    @Future
    private LocalDateTime deadline;
    private SubjectDTO subject;
    private StudyGroupDTO group;
    private TeacherDTO teacher;
}
