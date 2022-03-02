package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.StudyGroup;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.repositories.ControlFormDictRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.SubjectRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class SubjectInGroupService {

    private final SubjectRepository subjectRepository;
    private final SubInGroupRepository subInGroupRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final ControlFormDictRepository controlFormDictRepository;

    @Autowired
    public SubjectInGroupService(SubjectRepository subjectRepository, SubInGroupRepository subInGroupRepository, StudyGroupRepository studyGroupRepository, ControlFormDictRepository controlFormDictRepository) {
        this.subjectRepository = subjectRepository;
        this.subInGroupRepository = subInGroupRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.controlFormDictRepository = controlFormDictRepository;
    }

    public Iterable<Person> getStudentGroup(Integer groupId){
        return studyGroupRepository.findById(groupId)
                .map(StudyGroup::getUsers)
                .orElseGet(Collections::emptyList);
    }

    public Optional<SubInGroup> save(SubInGroup subInGroup){
        return Optional.of(subInGroupRepository.save(subInGroup));
    }

    public Iterable<SubInGroup> getAllSubInGroup(){
        return subInGroupRepository.findAll();
    }

    public void removeSubInGroup(Long subInGroupId){
        subInGroupRepository.deleteById(subInGroupId);
    }
}
