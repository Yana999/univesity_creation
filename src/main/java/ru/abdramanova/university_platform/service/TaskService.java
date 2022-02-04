package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Material;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.repositories.MaterialRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private SubInGroupRepository subInGroupRepository;

    @Autowired
    public void setSubInGroupRepository(SubInGroupRepository subInGroupRepository) {
        this.subInGroupRepository = subInGroupRepository;
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void initMaterial(){
    }
    public void initTasks(){
        taskRepository.save(new Task("Задача 1", "Решить задачи A - F контеста 1", LocalDateTime.of(2022, Month.MARCH, 11, 23, 59), subInGroupRepository.findById(1L).get()));
    }
}
