package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> personById(@PathVariable Long id){
        Optional<Person> personById = personService.getPersonById(id);
        if(personById.isPresent()){
            return ResponseEntity.ok(personById.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<Iterable<Person>> allPeople(){
        return ResponseEntity.ok(personService.getPeople());
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person){
        return personService.savePerson(person).get();
    }

    @GetMapping("/student")
    public ResponseEntity<List<Person>> getStudentBySurname(@RequestParam("surname") String surname){
        List<Person> students = personService.findStudentBySurname(surname);
        if(students.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(students);
    }

    @PatchMapping
    public ResponseEntity<Person> updatePerson(@PathVariable("surname") String surname, @PathVariable("id") Long id){
        Optional<Person> person = personService.getPersonById(id);
        if(person.isPresent()){
            person.get().setSurname(surname);
            return  ResponseEntity.ok(personService.updatePerson(person.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/students")
    public ResponseEntity<Iterable<Person>> students(){
        return ResponseEntity.ok(personService.getStudents());
    }

    @DeleteMapping("/deletePerson/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
    }


}
