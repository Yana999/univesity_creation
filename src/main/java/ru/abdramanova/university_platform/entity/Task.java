package ru.abdramanova.university_platform.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Task {
    @EmbeddedId
    private TaskKey taskKey;
    @Lob
    @NotNull
    @Column(nullable = false,length = 400)
    private String content;
    @Column(nullable = false)
    @Future
    private LocalDateTime deadline;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Material> materials;
    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<Assessment> assessments;
    @ManyToOne
    private SubInGroup subInfo;

    public Task() {
    }

    public Task(TaskKey taskKey, String content, LocalDateTime deadline, List<Material> materials, SubInGroup subInfo) {
        this.taskKey = taskKey;
        this.content = content;
        this.deadline = deadline;
        this.materials = materials;
        this.subInfo = subInfo;
    }

    public Task(TaskKey taskKey, String content, LocalDateTime deadline, SubInGroup subInfo) {
        this.taskKey = taskKey;
        this.content = content;
        this.deadline = deadline;
        this.subInfo = subInfo;
    }

    public TaskKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(TaskKey taskKey) {
        this.taskKey = taskKey;
    }

    public SubInGroup getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(SubInGroup subInfo) {
        this.subInfo = subInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
