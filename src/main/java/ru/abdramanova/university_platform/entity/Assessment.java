package ru.abdramanova.university_platform.entity;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Check(constraints = "assessment >= 0 AND assessment <= 100")
public class Assessment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "table-generator"
    )
    @TableGenerator(
            name =  "table-generator",
            table = "table_identifier",
            pkColumnName = "table_name",
            valueColumnName = "assessment_id",
            initialValue = 2,
            allocationSize = 3
    )
    private Long id;
    @Column(nullable = false)
    private int assessment;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime time;
    @ManyToOne
    private Person user;
    @ManyToOne
    private Task task;

    public Assessment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Assessment(int assessment, Person user, Task task) {
        this.assessment = assessment;
        this.user = user;
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


}
