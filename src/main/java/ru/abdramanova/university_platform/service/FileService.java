package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.abdramanova.university_platform.entity.Material;
import ru.abdramanova.university_platform.repositories.MaterialRepository;
import ru.abdramanova.university_platform.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final MaterialRepository materialRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public FileService(MaterialRepository materialRepository, TaskRepository taskRepository) {
        this.materialRepository = materialRepository;
        this.taskRepository = taskRepository;
    }

    public boolean save (MultipartFile file){
        try {
            Material material = new Material();
            material.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            material.setContentType(file.getContentType());
            material.setFile(file.getBytes());
            return materialRepository.save(material) == null ? true : false;

        }catch (Exception e){
            return false;
        }
    }

    public Optional<Material> getMaterial(Long id){
        return materialRepository.findById(id);
    }

    public List<Material> getFilesByTask(Long taskId){
        //return taskRepository.findById(taskId).get().getMaterials();
        return null;
    }

    public List<Material> getAllFiles() {
        return materialRepository.findAll();
    }
}
