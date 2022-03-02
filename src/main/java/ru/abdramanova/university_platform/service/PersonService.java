package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.PersonRole;
import ru.abdramanova.university_platform.entity.StudyGroup;
import ru.abdramanova.university_platform.repositories.AssessmentRepository;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.PersonRoleRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonRoleRepository personRoleRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final AssessmentRepository assessmentRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonRoleRepository personRoleRepository, StudyGroupRepository studyGroupRepository, AssessmentRepository assessmentRepository) {
        this.personRepository = personRepository;
        this.personRoleRepository = personRoleRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.assessmentRepository = assessmentRepository;
    }

    public Iterable<Person> getPeople(){
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }

    public Iterable<Person> getStudents(){
        return personRoleRepository.findById(2L)
                .map(PersonRole::getPeople)
                .orElseGet(Collections::emptyList);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> savePerson( Person person){
        if(person.getStudyGroup().getName() == null){
            Optional<StudyGroup> studyGroup = studyGroupRepository.findById(person.getStudyGroup().getId());
            if(studyGroup.isPresent()){
                person.setStudyGroup(studyGroup.get());
            }else {
                return Optional.empty();
            }
        }
        if(person.getPersonRole().getName() == null) {
            Optional<PersonRole> personRole = personRoleRepository.findById(person.getPersonRole().getId());
            if (personRole.isPresent()) {
                person.setPersonRole(personRole.get());
            }else return Optional.empty();
        }

        return Optional.of(personRepository.save(person));
    }

    public Optional<List<Person>> findStudentsByAssessment(Integer assessment){
        return Optional.ofNullable(assessmentRepository.findStudentsByAssessment(assessment));
    }

    public Optional<List<Person>> findStudentBySurname(String surname){
        return personRepository.findStudentBySurname(surname);
    }

    public Optional<Person> findStudentByLogin(String login){
        return personRepository.findPersonByLogin(login);
    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }


}
