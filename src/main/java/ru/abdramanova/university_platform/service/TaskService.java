package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SubInGroupRepository subInGroupRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, SubInGroupRepository subInGroupRepository) {
        this.taskRepository = taskRepository;
        this.subInGroupRepository = subInGroupRepository;
    }

    public List<Task> findTasksBySubInGroup(Long id){
        return subInGroupRepository.findById(id).map(SubInGroup::getTasks).orElseGet(Collections::emptyList);
    }

    public Task addOrUpdateTask(Task task){
       return taskRepository.save(task);
    }

}
