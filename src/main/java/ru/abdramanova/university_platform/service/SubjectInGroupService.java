package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.*;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;
import ru.abdramanova.university_platform.repositories.SubInGroupRepository;
import ru.abdramanova.university_platform.repositories.SubjectRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectInGroupService {

    private final SubInGroupRepository subInGroupRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final SubjectRepository subjectRepository;
    private final PersonRepository personRepository;


    @Autowired
    public SubjectInGroupService(SubInGroupRepository subInGroupRepository, StudyGroupRepository studyGroupRepository, SubjectRepository subjectRepository, PersonRepository personRepository) {
        this.subInGroupRepository = subInGroupRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.subjectRepository = subjectRepository;

        this.personRepository = personRepository;
    }

    public List<Person> getStudentGroup(Integer groupId){
        return studyGroupRepository.findById(groupId)
                .map(StudyGroup::getPeople)
                .orElseGet(Collections::emptyList);
    }

    public Optional<SubInGroup> save(SubInGroup subInGroup){
        Optional<Subject> subject = subjectRepository.findById(subInGroup.getSubject().getSubjectId());
        Optional<StudyGroup> group = studyGroupRepository.findById(subInGroup.getGroup().getGroupId());
        Optional<Person> teacher = personRepository.findById(subInGroup.getTeacher().getId());
        if(!subject.isPresent() && !group.isPresent() && !teacher.isPresent()) {
            return Optional.empty();
        }
        subInGroup.setSubject(subject.get());
        subInGroup.setGroup(group.get());
        subInGroup.setTeacher(teacher.get());
        return Optional.of(subInGroupRepository.save(subInGroup));
    }

    public Optional<SubInGroup> update (SubInGroup subInGroup){
        Optional<SubInGroup> sub = subInGroupRepository.findById(new SubInGroupId(subInGroup.getGroup().getGroupId(), subInGroup.getSubject().getSubjectId()));
        Optional<Subject> subject = subjectRepository.findById(subInGroup.getSubject().getSubjectId());
        Optional<StudyGroup> group = studyGroupRepository.findById(subInGroup.getGroup().getGroupId());
        Optional<Person> teacher = personRepository.findById(subInGroup.getTeacher().getId());
        if(!subject.isPresent() && !group.isPresent() && !teacher.isPresent()) {
            return Optional.empty();
        }
        sub.get().setSubject(subject.get());
        sub.get().setGroup(group.get());
        sub.get().setTeacher(teacher.get());
        return Optional.of(subInGroupRepository.save(sub.get()));
    }


    public Iterable<SubInGroup> getAllSubInGroup(){
        return subInGroupRepository.findAll();
    }

    public void removeSubInGroup(int groupId, int subjectId){
        subInGroupRepository.deleteById(new SubInGroupId(groupId, subjectId));
    }
}
