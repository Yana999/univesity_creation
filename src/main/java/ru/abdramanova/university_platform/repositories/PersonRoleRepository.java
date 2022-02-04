package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.abdramanova.university_platform.entity.PersonRole;

import java.util.List;

public interface PersonRoleRepository extends CrudRepository<PersonRole, Long> {
    List<PersonRole> findPersonRoleByName(String name);
}
