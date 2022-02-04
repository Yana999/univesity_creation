package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.Material;

public interface MaterialRepository extends CrudRepository<Material, Long> {
}
