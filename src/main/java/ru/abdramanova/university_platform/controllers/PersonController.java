package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.dto.PersonDTO;
import ru.abdramanova.university_platform.dto.PersonShortDTO;
import ru.abdramanova.university_platform.dto.PersonSimpleDTO;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.mappers.DTOMapper;
import ru.abdramanova.university_platform.service.AuthService;
import ru.abdramanova.university_platform.service.PersonService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private AuthService authController;
    @Autowired
    private DTOMapper dtoMapper;

//    @Autowired
//    public PersonController(PersonService personService, AuthService authController, DTOMapper dtoMapper) {
//        this.personService = personService;
//        this.authController = authController;
//        this.dtoMapper = dtoMapper;
//    }

    //выборка всех преподавателей
    @PreAuthorize("permitAll()")
    @GetMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PersonShortDTO> allTeachers(){
        return personService.getTeachers().stream().map(dtoMapper::teacherToDTO).collect(Collectors.toList());
    }

    //выборка по id
    //Просмотр информации о студентах для преподавателя
    @PreAuthorize("hasRole('ROLE_teacher')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonSimpleDTO personById(@PathVariable Long id){
        return  dtoMapper.simplePersonToDTO(personService.getStudentById(id).get());
    }


    //просмотр информации о себе для студента и преподавателя
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/account")
    @ResponseStatus(value = HttpStatus.OK)
    public PersonSimpleDTO toAccount(){
        return  dtoMapper.simplePersonToDTO(authController.getAuthUser().get());
    }


    // выборка по фамилии в репозитории запрос Query
    @PreAuthorize("hasAnyAuthority('ROLE_teacher', 'ROLE_student')")
    @GetMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PersonSimpleDTO> getStudentsBySurname(@RequestParam("surname") String surname){
        if(surname == null){
            return Collections.emptyList();
        }else {
            return dtoMapper.simplePersonListToDTO(personService.findStudentBySurname(surname).get());
        }
    }

    //редактирование информации о себе для преподавателя и студента
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_student')")
    @PutMapping
    public ResponseEntity<PersonSimpleDTO> updatePerson(@RequestBody @Valid PersonSimpleDTO person, Errors errors){
        if(!errors.hasErrors()){
            if(personService.updatePerson(dtoMapper.simplePersonDTOtoPerson(person)).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //регисрация ползователя администратором
    //@PreAuthorize("hasAnyRole('ROLE_admin')")
    @PreAuthorize("hasAnyRole('ROLE_teacher')")
    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid PersonDTO person, Errors errors){
        if(!errors.hasErrors()){
            Person person1 = dtoMapper.personDTOtoPerson(person);
            if(personService.savePerson(person1).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //обновлене фамилии человека по его id
    @Secured({"ROLE_teacher", "ROLE_student"})
    @PatchMapping("/{id}")
    public ResponseEntity<PersonSimpleDTO> updatePerson(@Valid @PathVariable("id") Long id, @RequestParam("surname") String surname){
        Optional<Person> person = personService.getStudentById(id);
        if (person.isPresent()) {
            person.get().setSurname(surname);
            return ResponseEntity.ok(dtoMapper.simplePersonToDTO(personService.updatePerson(person.get()).get()));
        }
        return ResponseEntity.notFound().build();
    }

    //удаление человека по id
    @RolesAllowed("hasRole('ROLE_admin')")
    @DeleteMapping()
    @ResponseStatus(code=HttpStatus.OK)
    public void deletePerson(@Valid @RequestParam("id") Long id){
        personService.deletePerson(id);
    }

    //не проверила, следать с диапозоном
    // выборка людей из группы с определенными оценками
    @PreAuthorize("hasRole('ROLE_teacher')")
    @GetMapping("/assessment")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonSimpleDTO> withAssessment (@Valid @RequestParam Integer assessment){
        return dtoMapper.simplePersonListToDTO(personService.findStudentsByAssessment(assessment).get());
    }
}
