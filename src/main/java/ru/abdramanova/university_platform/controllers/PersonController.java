package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //выборка всех людей
    @GetMapping()
    public ResponseEntity<Iterable<Person>> allPeople(){
        return ResponseEntity.ok(personService.getPeople());
    }

    //выборка по id
    @GetMapping("/{id}")
    public ResponseEntity<Person> personById(@Valid @PathVariable Long id){
        return  personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // выборка по фамилии в репозитории запрос Query
    @GetMapping("/student")
    public ResponseEntity<List<Person>> getStudentBySurname(@Valid @RequestParam("surname") String surname){
        return personService.findStudentBySurname(surname)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //выборка всех студентов
    @GetMapping("/student")
    public ResponseEntity<Iterable<Person>> students(){
        return ResponseEntity.ok(personService.getStudents());
    }

    //добавление и полное обновление данных о человеке
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person){
        if(personService.savePerson(person).isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.internalServerError().build();
    }

    //обновлене фамилии человека по его id
    @PatchMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@Valid @PathVariable("id") Long id, @RequestParam("surname") String surname){
        Optional<Person> person = personService.getPersonById(id);
        if(person.isPresent()){
            person.get().setSurname(surname);
            return  ResponseEntity.ok(personService.updatePerson(person.get()));
        }
        return ResponseEntity.badRequest().build();
    }

    //удаление человека по id
    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deletePerson(@Valid @PathVariable("id") Long id){
        personService.deletePerson(id);
    }

    // выборка людей из группы с определенными оценками
    @GetMapping("/assessment")
    public ResponseEntity<List<Person>> withAssessment (@Valid @RequestParam Integer assessment){
        return personService.findStudentsByAssessment(assessment)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
