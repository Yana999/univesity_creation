package ru.abdramanova.university_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.abdramanova.university_platform.dto.*;
import ru.abdramanova.university_platform.entity.Material;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.mappers.DTOMapper;
import ru.abdramanova.university_platform.service.AuthService;
import ru.abdramanova.university_platform.service.FileService;
import ru.abdramanova.university_platform.service.TaskService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final AuthService authController;
    private final FileService materialService;
    private final DTOMapper dtoMapper;


    @Autowired
    public TaskController(TaskService taskService, AuthService authController, FileService materialService, DTOMapper dtoMapper) {
        this.authController = authController;
        this.taskService = taskService;
        this.materialService = materialService;
        this.dtoMapper = dtoMapper;
    }

    //добавление дз преподавателем
    //проверка, что добавлять задания может только преподаватель этого курса
    @PreAuthorize("hasAnyRole('ROLE_teacher')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody @Valid FullTaskDTO task){

        taskService.addTask(dtoMapper.fullTaskDTOtoTask(task));
    }

    //редактирование дз преподаватель
    @PreAuthorize("hasRole('ROLE_teacher')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateTask(@RequestBody @Valid TaskDTO task){
        taskService.updateTask(dtoMapper.taskDTOtoTask(task));
    }

    //пустой список в материалах
    //просмотр список заданий студентами и учителями по конкретному предмету в конкретной группе
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_student')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getTasksInGroup(@RequestParam int groupId, @RequestParam int subjectId){
        List<Task> tasks = taskService.getTaskByGroupAndSubject(groupId, subjectId).get();
        List<TaskDTO> taskDTOS = dtoMapper.taskListToDTO(tasks);
        return taskDTOS;
    }

    //просмотр дз с оценками всех студентов для преподавателя по заданию в конкретной группе
    @PreAuthorize("hasAnyRole('ROLE_teacher')")
    @GetMapping("/assessments")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskWithAssessmentsDTO> getTasksForTeacher(@RequestParam Integer groupId, Integer subjectId){
        return dtoMapper.taskWithAssessToDTO(taskService.getAssessmentsBySubjectAndGroup(groupId, subjectId).get());
    }

    //получение файлов материалов для преподавателей и студентов
    @RolesAllowed({"ROLE_student", "ROLE_teacher"})
    @GetMapping("/material/{id}")
    public ResponseEntity<byte[]> getMaterial(@PathVariable Long id){
        Optional<Material> material = materialService.getMaterial(id);
        if(!material.isPresent()){
            return ResponseEntity.notFound().build();
        }
        MaterialDTO materialDTO = dtoMapper.materialToDTO(material.get());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + materialDTO.getName() + "\"")
                .contentType(MediaType.valueOf(materialDTO.getContentType()))
                .body(materialDTO.getFile());
    }

    //получение списка всех материалов
    @GetMapping("/material")
    public List<MaterialUrlDTO> list() {
        return dtoMapper.materialListUrlToDTO(materialService.getAllFiles());
    }

    //загрузка файлов матриалов
    @RolesAllowed("ROLE_teacher")
    @PostMapping("/material")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            materialService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

}
