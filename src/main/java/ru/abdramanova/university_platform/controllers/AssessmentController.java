package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.dto.AssessmentDTO;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.mappers.DTOMapper;
import ru.abdramanova.university_platform.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    private final TaskService taskService;
    private final DTOMapper dtoMapper;

    @Autowired
    public AssessmentController(TaskService taskService, DTOMapper dtoMapper) {
        this.taskService = taskService;
        this.dtoMapper = dtoMapper;
    }

    //проверить
    //Заполнение успеваемости для преподавателя
    @PreAuthorize("hasRole('ROLE_teacher')")
    @PostMapping("/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAssessments(@PathVariable long taskId, @RequestBody List<AssessmentDTO> assessments){
        taskService.addAllAssessments(taskId, dtoMapper.assessmentDTOListToAssessmentList(assessments));
        //сделать добавление только для одного задания
    }

    //редактирование успеваемости для преподавателя
    @PreAuthorize("hasRole('ROLE_teacher')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AssessmentDTO updateAssessment(@RequestBody AssessmentDTO assessment){
       return dtoMapper.assessmentToDTO(taskService.updateAssessment(dtoMapper.assessmentDTOtoAssessment(assessment)));
    }

}
