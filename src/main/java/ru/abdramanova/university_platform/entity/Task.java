package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Task implements Serializable {
    @EmbeddedId
    private TaskKey taskKey;
    @NotNull
    @Column(nullable = false,length = 400)
    private String content;
    @Column(nullable = false)
    @Future
    private LocalDateTime deadline;
    @OneToMany(mappedBy = "task", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Material> materials;
    @OneToMany(mappedBy = "task", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Assessment> assessments;
    @ManyToOne
    private SubInGroup subInfo;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

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

    @JsonIgnore
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
