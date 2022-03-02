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
import ru.abdramanova.university_platform.entity.FileResponse;
import ru.abdramanova.university_platform.entity.Material;
import ru.abdramanova.university_platform.entity.Task;
import ru.abdramanova.university_platform.service.FileService;
import ru.abdramanova.university_platform.service.TaskService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final AuthController authController;
    private final FileService materialService;


    @Autowired
    public TaskController(TaskService taskService, AuthController authController, FileService materialService) {
        this.authController = authController;
        this.taskService = taskService;
        this.materialService = materialService;
    }

    //добавление дз преподаватель
    //проверка, что добавлять задания может только преподаватель этого курса
    @PreAuthorize("hasAnyRole('ROLE_teacher')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    //редактирование дз преподаватель
    @PreAuthorize("hasAnyRole('ROLE_teacher')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@RequestBody Task task){
        return taskService.addTask(task);
    }


    //просмотр дз с оценками всех студентов для преподавателя по предмету в конкретной группе
    @PreAuthorize("hasAnyRole('ROLE_teacher')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Task> getTasksForTeacher(@RequestParam long subjectId){
        return taskService.getTaskBySubInGroup(subjectId).get();
    }

//    @PreAuthorize("hasAnyRole('ROLE_student')")
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<Task> getTasksForStudent(@RequestParam long subjectId){
//        return taskService.getTaskByStudentAndSubInGroup(subjectId, authController.getAuthUser().get().getId()).get();
//    }

    //получение файлов материалов для преподавателей и студентов
    @RolesAllowed({"ROLE_student", "ROLE_teacher"})
    @GetMapping("/material/{id}")
    public ResponseEntity<byte[]> getMaterial(@PathVariable Long id){
        Optional<Material> material = materialService.getMaterial(1L);
        if(!material.isPresent()){
            System.out.println("empty");
            return ResponseEntity.notFound().build();
        }
        System.out.println(material.get().getName());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + material.get().getName() + "\"")
                .contentType(MediaType.valueOf(material.get().getContentType()))
                .body(materialService.getMaterial(1L).get().getFile());
    }

    //получение списка всех материалов
    @GetMapping("/material")
    public List<FileResponse> list() {
        return materialService.getAllFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(Material fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/task/material/")
                .path(fileEntity.getId().toString())
                .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId().toString());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
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
