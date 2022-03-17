package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "invoice")
@EqualsAndHashCode(exclude = "invoice")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @NotNull
    @Column(nullable = false,length = 400)
    private String content;
    @Column(nullable = false)
    @Future
    private LocalDateTime deadline;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Task(String name, String content, LocalDateTime deadline, List<Material> materials, SubInGroup subInfo) {
        this.name = name;
        this.content = content;
        this.deadline = deadline;
        this.materials = materials;
        this.subInfo = subInfo;
    }

    public Task(String name, String content, LocalDateTime deadline, SubInGroup subInfo) {
        this.name = name;
        this.content = content;
        this.deadline = deadline;
        this.subInfo = subInfo;
    }
}
