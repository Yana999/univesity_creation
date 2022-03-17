package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@IdClass(SubInGroupId.class)
public class SubInGroup {

    @Column(nullable = false)
    @Future
    private LocalDateTime deadline;
    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Subject subject;
    @Id
    @ManyToOne
    private StudyGroup group;
    @OneToOne
    private Person teacher;
    @OneToMany(mappedBy = "subInfo", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    public SubInGroup(LocalDateTime deadline, Subject subject, StudyGroup group, Person teacher) {
        this.deadline = deadline;
        this.subject = subject;
        this.group = group;
        this.teacher = teacher;
    }

}
