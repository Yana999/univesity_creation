package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.abdramanova.university_platform.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonBySurnameAndName(String surname, String name);
    @Query("select p from Person p where p.surname = :surname")
    List<Person> findStudentBySurname(@Param("surname") String surname);
}
