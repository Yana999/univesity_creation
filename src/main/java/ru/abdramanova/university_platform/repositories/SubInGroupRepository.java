package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.entity.SubInGroupId;

public interface SubInGroupRepository extends CrudRepository<SubInGroup, SubInGroupId> {
}
