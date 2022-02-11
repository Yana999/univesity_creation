package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonBySurnameAndName(String surname, String name);
}
