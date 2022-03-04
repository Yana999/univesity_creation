package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.abdramanova.university_platform.entity.Person;

import java.util.Optional;

@Component
public class AuthService {

    private final PersonService personService;

    @Autowired
    public AuthService(PersonService personService) {
        this.personService = personService;
    }

    public Optional<Person> getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth ==null){
            return Optional.empty();
        }
        return personService.findStudentByLogin(auth.getName());
    }



}
