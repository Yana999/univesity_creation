package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.PersonService;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private PersonService personService;

    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/login")
    public @ResponseBody Person getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth ==null){
            return null;
        }

        Object principal = auth.getPrincipal();
        Person user = (principal instanceof Person) ? (Person) principal : null;
        System.out.println(auth.getName());
        System.out.println(user);
        return Objects.nonNull(user) ? this.personService.findStudentByLogin(user.getLogin()).get() : null;
    }



}
