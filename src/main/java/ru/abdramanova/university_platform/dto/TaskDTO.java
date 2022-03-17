package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class TaskDTO {

    private Long id;
    private String name;
    @NotNull
    private String content;
    @Future
    private LocalDateTime deadline;
    private List<MaterialUrlDTO> materials;

}
