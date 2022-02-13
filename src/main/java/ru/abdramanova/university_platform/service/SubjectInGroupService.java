package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.StudyGroup;
import ru.abdramanova.university_platform.repositories.ControlFormDictRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.SubjectRepository;

import java.util.Optional;

@Service
public class SubjectInGroupService {

    private SubjectRepository subjectRepository;
    private SubInGroupRepository subInGroupRepository;
    private StudyGroupRepository studyGroupRepository;
    private ControlFormDictRepository controlFormDictRepository;

    @Autowired
    public SubjectInGroupService(SubjectRepository subjectRepository, SubInGroupRepository subInGroupRepository, StudyGroupRepository studyGroupRepository, ControlFormDictRepository controlFormDictRepository) {
        this.subjectRepository = subjectRepository;
        this.subInGroupRepository = subInGroupRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.controlFormDictRepository = controlFormDictRepository;
    }

    public Optional<Iterable<Person>> getStudentGroup(Integer groupId){
        Optional<StudyGroup> studyGroup = studyGroupRepository.findById(groupId);
        if(studyGroup.isPresent()){
            return Optional.ofNullable(studyGroup.get().getUsers());
        }
        return Optional.empty();
    }

    public Page getContrForm(int number, int size){
        return controlFormDictRepository.findAll(PageRequest.of(number,size));
    }
}
