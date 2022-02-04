package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.StudyGroup;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;

import java.util.ArrayList;

@Service
public class StudyGroupService {

    private StudyGroupRepository studyGroupRepository;


    @Autowired
    public void setStudyGroupRepository(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    public void initGroup(){
        studyGroupRepository.save(new StudyGroup("МПИ-21-1-7"));
        studyGroupRepository.save(new StudyGroup("МИВТ-21-2-4"));
    }
}
