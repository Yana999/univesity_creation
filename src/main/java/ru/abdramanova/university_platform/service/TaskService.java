package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.entity.TaskKey;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private SubInGroupRepository subInGroupRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, SubInGroupRepository subInGroupRepository) {
        this.taskRepository = taskRepository;
        this.subInGroupRepository = subInGroupRepository;
    }

    public Optional<List<Task>> findTasksBySubInGroup(Long id){
        return Optional.ofNullable(subInGroupRepository.findById(id).get().getTasks());
    }

    public Task addOrUpdateTask(Task task){
       return taskRepository.save(task);
    }

}
