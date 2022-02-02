package ru.abdramanova.univesity_creation.Entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class SubInGroup {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private ZonedDateTime deadline;
    @OneToMany(mappedBy = "subjectInfo")
    private List<Subject> subjects;
    @ManyToOne
    private StudyGroup group;
    @OneToOne
    private Person teacher;
    @OneToMany(mappedBy = "subInfo")
    private List<Task> tasks;

    public SubInGroup() {
    }

    public SubInGroup(ZonedDateTime deadline, List<Subject> subjects, StudyGroup group, Person teacher, List<Task> tasks) {
        this.deadline = deadline;
        this.subjects = subjects;
        this.group = group;
        this.teacher = teacher;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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
