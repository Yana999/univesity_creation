package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.PersonService;

import javax.validation.Valid;
import java.util.Collection;
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
//    @GetMapping("/{id}")
//    public ResponseEntity<Person> personById(@PathVariable Long id){
//        return  ResponseEntity.ok(personService.getPersonById(id).get());
//    }

    @GetMapping("/account")
    @ResponseStatus(value = HttpStatus.OK)
    public Person toAccount(@AuthenticationPrincipal Person user){
        return  user;
    }

    // выборка по фамилии в репозитории запрос Query
    @GetMapping("/student")
    public ResponseEntity<Iterable<Person>> getStudentBySurname(@RequestParam("surname") String surname){
        if(surname == null){
            return ResponseEntity.ok(personService.getStudents());
        }else {
            return ResponseEntity.ok(personService.findStudentBySurname(surname).get());
        }
    }

    //добавление и полное обновление данных о человеке
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Person> addPerson(@RequestBody @Valid Person person,  Errors errors){
        if(!errors.hasErrors()){
            if(personService.savePerson(person).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //обновлене фамилии человека по его id
    @PatchMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@Valid @PathVariable("id") Long id, @RequestParam("surname") String surname){
        Optional<Person> person = personService.getPersonById(id);
        person.get().setSurname(surname);
        return  ResponseEntity.ok(personService.updatePerson(person.get()));
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
        return ResponseEntity.ok(personService.findStudentsByAssessment(assessment).get());
    }
}
