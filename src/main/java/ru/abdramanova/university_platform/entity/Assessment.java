package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Check(constraints = "assessment >= 0 AND assessment <= 100")
@Setter
@Getter
@NoArgsConstructor
public class Assessment  implements Serializable {
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
    @Range(min = -1, max = 101)
    private int assessment;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime time;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Person user;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Task task;

    public Assessment(int assessment, Person user, Task task) {
        this.assessment = assessment;
        this.user = user;
        this.task = task;
    }
}
