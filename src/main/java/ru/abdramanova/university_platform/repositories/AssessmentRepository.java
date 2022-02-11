package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.abdramanova.university_platform.entity.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
