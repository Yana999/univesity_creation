package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.entity.ControlFormDict;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.service.SubjectInGroupService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {

    private final SubjectInGroupService subjectInGroupService;

    @Autowired
    public SubjectController(SubjectInGroupService subjectInGroupService) {
        this.subjectInGroupService = subjectInGroupService;
    }

    //получение списка студентов в группе для преподавателя и студента
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_student')")
    @GetMapping("/group/student")
    public ResponseEntity<Iterable<Person>> studentsInGroup(@Valid @RequestParam Integer groupId){
        return ResponseEntity.ok(subjectInGroupService.getStudentGroup(groupId));
    }


    //добавление предметов админом
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/subject/group")
    @ResponseStatus(HttpStatus.CREATED)
    public SubInGroup addSubInGroup(@RequestBody SubInGroup subInGroup){
        return subjectInGroupService.save(subInGroup).get();
    }

    //получение всех предметов в группах
    @PreAuthorize("hasRole('ROLE_student')")
    @GetMapping("/subject/group")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SubInGroup> getAllSubInGroup(){
        return (subjectInGroupService.getAllSubInGroup());
    }


    //удаление предметов админом
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/subject/group")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubInGroup(@RequestParam long subInGroupId){
        subjectInGroupService.removeSubInGroup(subInGroupId);
    }

    //редактирование предметов админом
    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping
    public ResponseEntity<SubInGroup> updatePerson(@RequestBody @Valid SubInGroup subInGroup,  Errors errors){
        if(!errors.hasErrors()){
            if(subjectInGroupService.save(subInGroup).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
