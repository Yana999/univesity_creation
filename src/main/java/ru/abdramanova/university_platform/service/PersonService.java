package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.PersonRole;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.PersonRoleRepository;
import ru.abdramanova.university_platform.repositories.StudyGroupRepository;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private PersonRoleRepository personRoleRepository;

    private StudyGroupRepository studyGroupRepository;

    @Autowired
    public void setStudyGroupRepository(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    public void initPersonRole(){
        personRoleRepository.save(new PersonRole("Преподаватель"));
        personRoleRepository.save(new PersonRole("Студент"));
        personRoleRepository.save(new PersonRole("Администратор"));
    }

    // подумать над регистром
    public void initPerson(){
        personRepository.save(new Person("Абдраманова","Яна", "Расимовна", "89628569064", "Abdramanova.yana@yandex.ru", studyGroupRepository.findByName("МПИ-21-1-7").get(0), personRoleRepository.findPersonRoleByName("Студент").get(0)));
        personRepository.save(new Person("Новикова", "Полина", "Сергеевна", "89876543211", "PS1234@yandex.ru", studyGroupRepository.findByName("МИВТ-21-2-4").get(0), personRoleRepository.findPersonRoleByName("Студент").get(0)));
        personRepository.save(new Person("Бирина", "Венера", "Юрьевна", "89998887766", "BirinaV@mail.ru", personRoleRepository.findPersonRoleByName("Преподаватель").get(0)));
        personRepository.save(new Person("Битаров", "Костантин","Эльбрусович", "891112223344", "TheNoneMan@gmai.com", studyGroupRepository.findByName("МПИ-21-1-7").get(0), personRoleRepository.findPersonRoleByName("Студент").get(0)));
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setPersonRoleRepository(PersonRoleRepository personRoleRepository) {
        this.personRoleRepository = personRoleRepository;
    }
}
