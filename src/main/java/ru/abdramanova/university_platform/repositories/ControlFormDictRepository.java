package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.abdramanova.university_platform.entity.ControlFormDict;

import java.util.List;

@Repository
public interface ControlFormDictRepository extends CrudRepository<ControlFormDict, Short> {
    List<ControlFormDict> findByName(String name);
}