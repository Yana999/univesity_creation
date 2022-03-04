package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    private final TaskService taskService;

    @Autowired
    public AssessmentController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Заполнение успеваемости для преподавателя
    @PreAuthorize("hasRole('ROLE_teacher')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAssessments(@RequestBody List<Assessment> assessmentList){
        taskService.addAllAssessments(assessmentList);
        //сделать добавление только для одного задания
    }

    //редактирование успеваемости для преподавателя
    @PreAuthorize("hasRole('ROLE_teacher')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAssessments(@RequestBody List<Assessment> assessments){
        taskService.addAllAssessments(assessments);
    }

    //просмотр успеваемости для студента
//    @PreAuthorize("hasAnyRole('teacher, student')")
//    @GetMapping("/id")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Assessment> getAssessmentByStudent(@PathVariable Long id){
//        taskService.
//    }

}
