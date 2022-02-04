package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.repositories.AssessmentRepository;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

@Service
public class AssessmentService {

    private AssessmentRepository assessmentRepository;

    private PersonRepository personRepository;

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setAssessmentRepository(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public void initAssessments(){
        assessmentRepository.save(new Assessment(56, personRepository.findPersonBySurnameAndName("Абдраманова", "Яна").get(0), taskRepository.findById(1L).get()));
        assessmentRepository.save(new Assessment(71, personRepository.findPersonBySurnameAndName("Новикова", "Полина").get(0), taskRepository.findById(1L).get()));
    }
}
