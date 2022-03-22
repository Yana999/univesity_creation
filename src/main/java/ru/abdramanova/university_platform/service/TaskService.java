package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abdramanova.university_platform.entity.*;
import ru.abdramanova.university_platform.repositories.AssessmentRepository;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Transactional
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

    @Transactional
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


    public Optional<List<Task>> getTasksBySubjectAndGroup(int groupId, int subjectId){
        Optional<SubInGroup> subInGroup = subInGroupRepository.findById(new SubInGroupId(groupId, subjectId));
        if(!subInGroup.isPresent()){
            return Optional.empty();
        }
        return Optional.of(subInGroup.get().getTasks());
    }

    @Transactional(rollbackFor = {RuntimeException.class, NoSuchElementException.class})
    public List<Assessment> addAllAssessments (long taskId, List<Assessment> assessments) throws NoSuchElementException {
        Optional<Task> task  = taskRepository.findById(taskId);
        if (!task.isPresent()){
            throw new NoSuchElementException("Cannot find related task");
        }
        assessments.stream().forEach(assess -> assess.setTask(task.get()));
        for (int i = 0; i < assessments.size(); ++i) {
            Optional<Person> student = personRepository.findById(assessments.get(i).getStudent().getId());
            if(student.isPresent()){
                assessments.get(i).setStudent(student.get());
            }else throw new NoSuchElementException("No such student");
        }
        return assessmentRepository.saveAll(assessments);
    }


    //внутри проставления оценки можно сделать расчет за семестр на основании стандартных метрик
    public Assessment updateAssessment(Long taskId, Assessment assessment) throws NoSuchElementException{
        Optional<Assessment> asses = assessmentRepository.findById(taskId);
        if(!asses.isPresent()){
            throw new NoSuchElementException("Cannot find updatable assessment");
        }
        asses.get().setAssessment(assessment.getAssessment());
        return assessmentRepository.save(asses.get());
    }

    public Assessment addAssessment(Long taskId, Assessment assessment) throws NoSuchElementException{
        Optional<Task> task = taskRepository.findById(taskId);
        //Optional<Assessment> student = task.get().getAssessments().stream().filter(x -> x.getStudent().getId().equals(assessment.getStudent().getId())).findAny();
        if(!task.isPresent()){
            throw new NoSuchElementException("No such Task");
        }
        assessment.setTask(task.get());
        return assessmentRepository.save(assessment);
    }

}
