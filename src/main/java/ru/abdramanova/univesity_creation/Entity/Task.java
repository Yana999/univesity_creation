package ru.abdramanova.univesity_creation.Entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(nullable = false,length = 400)
    private String content;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime deadline;
    @OneToMany(mappedBy = "task",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Material> materials;
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Assessment> assessments;
    @ManyToOne
    private SubInGroup subInfo;

    public Task() {
    }

    public Task(String name, String content, ZonedDateTime deadline, List<Material> materials, List<Assessment> assessments, SubInGroup subInfo) {
        this.name = name;
        this.content = content;
        this.deadline = deadline;
        this.materials = materials;
        this.assessments = assessments;
        this.subInfo = subInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SubInGroup getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(SubInGroup subInfo) {
        this.subInfo = subInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime deadline) {
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
