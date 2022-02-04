package ru.abdramanova.university_platform.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class SubInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
    @ManyToOne
    private StudyGroup group;
    @OneToOne(fetch = FetchType.LAZY)
    private Person teacher;
    @OneToMany(mappedBy = "subInfo")
    private List<Task> tasks;

    public SubInGroup() {
    }

    public SubInGroup(LocalDateTime deadline, Subject subject, StudyGroup group, Person teacher) {
        this.deadline = deadline;
        this.subject = subject;
        this.group = group;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public StudyGroup getGroup() {
        return group;
    }

    

    public void setGroup(StudyGroup group) {
        this.group = group;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }
}
