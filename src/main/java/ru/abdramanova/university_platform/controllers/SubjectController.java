package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.ControlFormDict;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.SubjectInGroupService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
public class SubjectController {

    private final SubjectInGroupService subjectInGroupService;

    @Autowired
    public SubjectController(SubjectInGroupService subjectInGroupService) {
        this.subjectInGroupService = subjectInGroupService;
    }

    //получение списка студентов в группе
    @GetMapping
    public ResponseEntity<Iterable<Person>> studentsInGroup(@Valid @RequestParam Integer groupId){
        return ResponseEntity.ok(subjectInGroupService.getStudentGroup(groupId));
    }

    //
    @GetMapping("/control")
    public ResponseEntity<List<ControlFormDict>> getControlForms(@RequestParam Integer page){
        return ResponseEntity.ok(subjectInGroupService.getContrForm(page, 2).getContent());
    }
}
