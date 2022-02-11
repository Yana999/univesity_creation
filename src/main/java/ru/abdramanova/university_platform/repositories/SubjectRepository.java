package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
List<Subject> findByName(String name);
Subject saveAndFlush(Subject entity);
}
