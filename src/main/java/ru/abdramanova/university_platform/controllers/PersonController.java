package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.AuthService;
import ru.abdramanova.university_platform.service.PersonService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final AuthService authController;

    @Autowired
    public PersonController(PersonService personService, AuthService authController) {
        this.personService = personService;
        this.authController = authController;
    }

    //выборка всех людей
    @GetMapping()
    public ResponseEntity<Iterable<Person>> allPeople(){
        return ResponseEntity.ok(personService.getPeople());
    }

    //выборка по id
    //Просмотр информации о людях для преподавателя
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<Person> personById(@PathVariable Long id){
        return  ResponseEntity.ok(personService.getPersonById(id).get());
    }


    //просмотр информации о себе для студента и преподавателя
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/account")
    @ResponseStatus(value = HttpStatus.OK)
    public Person toAccount(){
        System.out.println(authController.getAuthUser());
        return  authController.getAuthUser().get();
    }

    // выборка по фамилии в репозитории запрос Query
    //получение списка студентов для преподавателя
    @PreAuthorize("hasAuthority('ROLE_teacher')")
    @GetMapping("/student")
    public ResponseEntity<Iterable<Person>> getStudentBySurname(@RequestParam("surname") String surname){
        if(surname == null){
            return ResponseEntity.ok(personService.getStudents());
        }else {
            return ResponseEntity.ok(personService.findStudentBySurname(surname).get());
        }
    }

    //редактирование информации о себе для преподавателя и студента
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_student')")
    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person person,  Errors errors){
        if(!errors.hasErrors()){
            if(personService.savePerson(person).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //добавление информации о себе для преподавателя и студента
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_student')")
    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody @Valid Person person,  Errors errors){
        if(!errors.hasErrors()){
            if(personService.savePerson(person).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //обновлене фамилии человека по его id

    @Secured({"ROLE_teacher", "ROLE_student"})
    @PatchMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@Valid @PathVariable("id") Long id, @RequestParam("surname") String surname){
        Optional<Person> person = personService.getPersonById(id);
        person.get().setSurname(surname);
        return  ResponseEntity.ok(personService.updatePerson(person.get()));
    }

    //удаление человека по id
    @RolesAllowed("hasRole('ROLE_admin')")
    @DeleteMapping()
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deletePerson(@Valid @RequestParam("id") Long id){
        personService.deletePerson(id);
    }

    // выборка людей из группы с определенными оценками
    @PreAuthorize("hasRole('ROLE_teacher')")
    @GetMapping("/assessment")
    public ResponseEntity<List<Person>> withAssessment (@Valid @RequestParam Integer assessment){
        return ResponseEntity.ok(personService.findStudentsByAssessment(assessment).get());
    }

}
