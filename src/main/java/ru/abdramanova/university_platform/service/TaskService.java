package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.entity.SubInGroupId;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.repositories.AssessmentRepository;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

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

    public Task addTask(Task task){
        System.out.println(task.getSubInfo().getGroup().getGroupId());
        System.out.println(task.getSubInfo().getSubject().getSubjectId());
        Optional<SubInGroup> sub = subInGroupRepository.findById(new SubInGroupId(task.getSubInfo().getGroup().getGroupId(), task.getSubInfo().getSubject().getSubjectId()));
        if(!sub.isPresent()){
            return null;
        }
        task.setSubInfo(sub.get());

        return taskRepository.save(task);
    }

    public Task updateTask(Task task){
        Optional<Task> curTask = taskRepository.findById(task.getId());
        if(!curTask.isPresent()){
            return null;
        }
        Task t = curTask.get();
        t.setName(task.getName());
        t.setContent(task.getContent());
        t.setDeadline(task.getDeadline());
        t.setMaterials(task.getMaterials());

        return taskRepository.save(t);
    }

    public Optional<List<Task>>  getTaskByGroupAndSubject(int groupId, int subjectId){
        Optional<SubInGroup> subInGroup = subInGroupRepository.findById(new SubInGroupId(groupId, subjectId));
        if (!subInGroup.isPresent()) {
            return Optional.empty();
        }
        List<Task> tasks = subInGroup.get().getTasks();
        return Optional.ofNullable(tasks);
    }

    public Optional<List<Task>> getAssessmentsBySubjectAndGroup(int groupId, int subjectId){
        Optional<SubInGroup> subInGroup = subInGroupRepository.findById(new SubInGroupId(groupId, subjectId));
        if(!subInGroup.isPresent()){
            return Optional.empty();
        }
        return Optional.of(subInGroup.get().getTasks());
    }

}
