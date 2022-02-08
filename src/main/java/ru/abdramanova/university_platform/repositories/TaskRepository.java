package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.entity.TaskKey;

public interface TaskRepository extends JpaRepository<Task, TaskKey> {
}
