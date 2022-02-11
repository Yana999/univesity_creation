package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.PersonRole;
import ru.abdramanova.university_platform.entity.StudyGroup;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.PersonRoleRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonRoleRepository personRoleRepository;
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonRoleRepository personRoleRepository, StudyGroupRepository studyGroupRepository) {
        this.personRepository = personRepository;
        this.personRoleRepository = personRoleRepository;
        this.studyGroupRepository = studyGroupRepository;
    }

    public Iterable<Person> getPeople(){
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }

    public Iterable<Person> getStudents(){
        return personRoleRepository.findById(2L).get().getPeople();
    }

    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public Optional<Person> savePerson(Person person){
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

    public List<Person> findStudentBySurname(String surname){
        return personRepository.findStudentBySurname(surname);
    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }
}
