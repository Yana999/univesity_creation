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


    public List<Person> getTeachers(){
        Optional<PersonRole> teachers = personRoleRepository.findPersonRoleByNameIgnoreCase("teacher");
        if(teachers.isPresent()){
            return personRoleRepository.findPersonRoleByNameIgnoreCase("teacher").get().getPeople();
        }
        return Collections.emptyList();
    }

    public Optional<Person> getStudentById(Long personId){
        PersonRole personRole = personRoleRepository.findPersonRoleByNameIgnoreCase("student").get();
        return personRepository.findByPersonRoleIdAndId(personRole.getId(), personId);
    }

    public List<Person> getStudents(){
        return personRoleRepository.findPersonRoleByNameIgnoreCase("student")
                .map(PersonRole::getPeople)
                .orElseGet(Collections::emptyList);
    }

    public Optional<Person> updatePerson(Person person) {
        Person person1 = personRepository.findById(person.getId()).orElseGet(null);
        person1.setName(person.getName());
        person1.setSurname(person.getSurname());
        person1.setSecondName(person.getSecondName());
        person1.setEmail(person.getEmail());
        person1.setPhoneNumber(person.getPhoneNumber());
        person1.setStudyGroup(studyGroupRepository.findById(person.getStudyGroup().getGroupId()).get());
        return Optional.of(personRepository.save(person1));
    }

    public Optional<Person> savePerson( Person person){

        if(person.getStudyGroup().getName() == null){
            Optional<StudyGroup> studyGroup = studyGroupRepository.findById(person.getStudyGroup().getGroupId());
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
