package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.SubjectInGroupService;

import javax.validation.Valid;
import java.util.List;
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
    public ResponseEntity<Iterable<Person>> studentsInGroup(@Valid @RequestParam Integer groupId){
        Optional<Iterable<Person>> students = subjectInGroupService.getStudentGroup(groupId);
        if(students.isPresent()){
            return ResponseEntity.ok(students.get());
        }
         return ResponseEntity.notFound().build();
    }

    @GetMapping("/control")
    public ResponseEntity<List> getContrForms(@RequestParam Integer page){
        return ResponseEntity.ok(subjectInGroupService.getContrForm(page, 2).getContent());
    }


}
