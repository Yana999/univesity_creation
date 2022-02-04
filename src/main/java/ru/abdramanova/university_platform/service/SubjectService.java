package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.ControlFormDict;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.entity.Subject;
import ru.abdramanova.university_platform.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;

@Service
public class SubjectService {

    private ControlFormDictRepository controlFormDictRepository;

    private SubjectRepository subjectRepository;

    private SubInGroupRepository subInGroupRepository;

    private StudyGroupRepository studyGroupRepository;

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setStudyGroupRepository(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Autowired
    public void setControlFormDictRepository(ControlFormDictRepository controlFormDictRepository) {
        this.controlFormDictRepository = controlFormDictRepository;
    }

    @Autowired
    public void setSubInGroupRepository(SubInGroupRepository subInGroupRepository) {
        this.subInGroupRepository = subInGroupRepository;
    }

    public void initControlForm(){
        controlFormDictRepository.save(new ControlFormDict("зачет"));
        controlFormDictRepository.save(new ControlFormDict("экзамен"));
    }

    public void initSubjects(){
        subjectRepository.save(new Subject("Комбинаторика", controlFormDictRepository.findByName("экзамен").get(0)));
        subjectRepository.save(new Subject("Английский", controlFormDictRepository.findByName("зачет").get(0)));
        subjectRepository.save(new Subject("Квантовая информатика", controlFormDictRepository.findByName("зачет").get(0)));
    }


    // нужна проверка на принадлежность к преподавателю
    public void addInfo(){
//        subInGroupRepository.save(new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 23, 23, 59),
//                subjectRepository.findByName("Комбинаторика").get(0),
//                studyGroupRepository.findByName("МПИ-21-1-7").get(0),
//                personRepository.findPersonBySurnameAndName("Бирина", "Венера").get(0)));
        subInGroupRepository.save(new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 23, 23, 59),
subjectRepository.findByName("Комбинаторика").get(0),studyGroupRepository.findByName("МПИ-21-1-7").get(0),personRepository.findPersonBySurnameAndName("Бирина", "Венера").get(0)));
    }
}
