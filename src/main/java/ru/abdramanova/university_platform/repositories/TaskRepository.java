package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
