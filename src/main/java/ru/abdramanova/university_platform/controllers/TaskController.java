package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.abdramanova.university_platform.service.TaskService;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //добавление дз преподаватель

    //редактирование дз преподаватель


}
