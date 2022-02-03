package ru.abdramanova.univesity_creation.Entity;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.xml.validation.Schema;
import java.time.ZonedDateTime;

@Entity
@Table(schema = "Students_platform")
@Check(constraints = "assessment >= 0 AND assessment <= 100")
public class Assessment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "table-generator"
    )
    @TableGenerator(
            name =  "table-generator",
            table = "Students_platform.table_identifier",
            pkColumnName = "table_name",
            valueColumnName = "assessment_id",
            initialValue = 2,
            allocationSize = 3
    )
    private long id;
    @Column(nullable = false)
    private int assessment;
    @UpdateTimestamp
    @Column(nullable = false)
    private ZonedDateTime time;
    @ManyToOne
    private Person user;
    @ManyToOne
    private Task task;

    public Assessment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Assessment(int assessment, ZonedDateTime time, Person user, Task task) {
        this.assessment = assessment;
        this.time = time;
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
