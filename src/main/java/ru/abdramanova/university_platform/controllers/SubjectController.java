package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.SubjectInGroupService;

import java.util.Optional;

@RestController
@RequestMapping("/group")
public class SubjectController {

    private SubjectInGroupService subjectInGroupService;

    @Autowired
    public SubjectController(SubjectInGroupService subjectInGroupService) {
        this.subjectInGroupService = subjectInGroupService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Person>> studentsInGroup(@RequestParam Integer id){
        Optional<Iterable<Person>> students = subjectInGroupService.getStudentGroup(id);
        if(students.isPresent()){
            return ResponseEntity.ok(students.get());
        }
         return ResponseEntity.notFound().build();
    }
}
