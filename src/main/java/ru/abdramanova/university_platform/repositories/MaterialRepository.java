package ru.abdramanova.university_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdramanova.university_platform.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
