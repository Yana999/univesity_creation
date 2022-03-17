package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.entity.Material;
import ru.abdramanova.university_platform.entity.SubInGroup;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FullTaskDTO {
    private Long id;
    private String name;
    @NotNull
    private String content;
    @Future
    private LocalDateTime deadline;
    private List<MaterialDTO> materials;
    private SubInGroupDTO subInfo;
}
