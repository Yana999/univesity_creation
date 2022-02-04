package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.StudyGroup;

import java.util.List;

public interface StudyGroupRepository extends CrudRepository<StudyGroup, Integer> {
    List<StudyGroup> findByName(String name);
}
