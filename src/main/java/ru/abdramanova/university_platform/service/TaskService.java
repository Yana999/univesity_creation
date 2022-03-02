package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.repositories.AssessmentRepository;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SubInGroupRepository subInGroupRepository;
    private final PersonRepository personRepository;
    private final AssessmentRepository assessmentRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, SubInGroupRepository subInGroupRepository, PersonRepository personRepository, AssessmentRepository assessmentRepository) {
        this.taskRepository = taskRepository;
        this.subInGroupRepository = subInGroupRepository;
        this.personRepository = personRepository;
        this.assessmentRepository = assessmentRepository;
    }

    public List<Task> findTasksBySubInGroup(Long id){
        return subInGroupRepository.findById(id).map(SubInGroup::getTasks).orElseGet(Collections::emptyList);
    }

    public Task addTask(Task task){
        Optional<SubInGroup> sub = subInGroupRepository.findById(task.getSubInfo().getId());
        if(!sub.isPresent()){
            return null;
        }
        task.setSubInfo(sub.get());
        task.getTaskKey().setId(new Timestamp( System.currentTimeMillis()));

        return taskRepository.save(task);
    }

    public Optional<Iterable<Task>>  getTaskBySubInGroup(Long subId){
        Optional<SubInGroup> subInGroup = subInGroupRepository.findById(subId);
        if (!subInGroup.isPresent()) {
            return Optional.empty();
        }
        List<Task> tasks = subInGroupRepository.findById(subId).get().getTasks();
        return Optional.ofNullable(tasks);
    }


    //убрать людей и оценки
    public Optional<Iterable<Task>>  getTaskByStudentAndSubInGroup(Long subId, Long studentId){
        Optional<SubInGroup> subInGroup = subInGroupRepository.findById(subId);
        if (!subInGroup.isPresent()) {
            return Optional.empty();
        }
        List<Task> tasks = subInGroupRepository.findById(subId).get().getTasks();
        return Optional.ofNullable(tasks);
    }

    public List<Assessment> addAllAssessment(List<Assessment> assessments){
        List<Task> tasks = taskRepository.findAllById(assessments.stream().map(x -> x.getTask().getTaskKey()).distinct().collect(Collectors.toList()));
        assessments.stream().forEach(x -> x.setTask(tasks.stream().filter(task -> task.getTaskKey().equals(x.getTask().getTaskKey())).findFirst().get()));
        return assessmentRepository.saveAll(assessments);
    }

}
