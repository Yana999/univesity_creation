package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abdramanova.university_platform.entity.Assessment;
import ru.abdramanova.university_platform.entity.Person;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Person> findStudentsByAssessment(Integer assessment);
}
