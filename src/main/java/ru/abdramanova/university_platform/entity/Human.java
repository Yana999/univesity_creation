package ru.abdramanova.university_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.abdramanova.university_platform.config.SecurityConfig;

import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@MappedSuperclass
@Setter
@Getter
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
    @NotBlank
    protected String secondName;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @Column(unique = true, nullable = false)
    private String login;

    public Human(String surname, String name, String secondName, String password, String login) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        setPassword(password);
        this.login = login;
    }

    public void setPassword (String password){
        this.password = SecurityConfig.PASSWORD_ENCODER.encode(password);
    }

}
