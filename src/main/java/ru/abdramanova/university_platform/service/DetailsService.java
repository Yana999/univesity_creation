package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.repositories.PersonRepository;

@Service
public class DetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public DetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findPersonByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));

        return User.builder()
                .username(person.getLogin())
                .password(person.getPassword())
                .roles(person.getPersonRole().getName())
                .build();
    }
}
