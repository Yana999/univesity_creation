package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.PersonService;

import java.util.Objects;
import java.util.Optional;

@Component
public class AuthController {

    private final PersonService personService;

    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    //@PostMapping("/login")
    public Optional<Person> getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth ==null){
            return Optional.empty();
        }

        Object principal = auth.getPrincipal();
        Person user = (principal instanceof Person) ? (Person) principal : null;
        System.out.println("name   " + auth.getName());
        System.out.println("user   " + user);
        System.out.println("role   " + auth.getAuthorities());
        return personService.findStudentByLogin(auth.getName());
    }



}
