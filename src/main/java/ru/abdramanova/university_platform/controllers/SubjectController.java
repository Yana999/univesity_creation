package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.abdramanova.university_platform.dto.PersonSimpleDTO;
import ru.abdramanova.university_platform.dto.SubInGroupDTO;
import ru.abdramanova.university_platform.entity.SubInGroup;
import ru.abdramanova.university_platform.mappers.DTOMapper;
import ru.abdramanova.university_platform.service.SubjectInGroupService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {

    private final SubjectInGroupService subjectInGroupService;
    private final DTOMapper dtoMapper;

    @Autowired
    public SubjectController(SubjectInGroupService subjectInGroupService, DTOMapper dtoMapper) {
        this.subjectInGroupService = subjectInGroupService;
        this.dtoMapper = dtoMapper;
    }

    //для несуществующих id сделать notFound вместо пустого списка
    //получение списка студентов в группе для преподавателя и студента
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_student')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/group/student")
    public List<PersonSimpleDTO> studentsInGroup(@Valid @RequestParam Integer groupId){
        return dtoMapper.simplePersonListToDTO(subjectInGroupService.getStudentGroup(groupId));
    }


    //сопоставление предметов преподавателю и предмету админом
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/subject/group")
    @ResponseStatus(HttpStatus.CREATED)
    public SubInGroupDTO addSubInGroup(@RequestBody SubInGroupDTO subInGroup){
        return dtoMapper.subInGroupToDTO(subjectInGroupService.save(dtoMapper.subInGroupDTOtoSubInGroup(subInGroup)).get());
    }

    //удаление предметов админом
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/subject/group")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubInGroup(@RequestParam int groupId, @RequestParam int subjectId){
        subjectInGroupService.removeSubInGroup(groupId, subjectId );
    }

    //редактирование предметов админом
    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping("/subject/group")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubInGroupDTO> updatePerson(@RequestBody @Valid SubInGroupDTO subInGroup,  Errors errors){
        Optional<SubInGroup> sub = subjectInGroupService.update(dtoMapper.subInGroupDTOtoSubInGroup(subInGroup));
        if(!errors.hasErrors()){
            if(sub.isPresent()){
                return ResponseEntity.of(Optional.of(dtoMapper.subInGroupToDTO(sub.get())));
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
