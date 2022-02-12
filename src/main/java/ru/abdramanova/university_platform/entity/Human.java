package ru.abdramanova.university_platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Human {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_generator"
    )
    @GenericGenerator(
            name = "user_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "3"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    protected Long id;
    @Column(nullable = false)
    @NotBlank
    protected String surname;
    @Column(nullable = false)
    @NotBlank
    protected String name;
    @Column(nullable = false)
    @NotBlank
    protected String secondName;

    public Human(String surname, String name, String secondName) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
    }
}
