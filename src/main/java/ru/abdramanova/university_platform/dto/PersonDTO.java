package ru.abdramanova.university_platform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
public class PersonDTO {

    private String id;
    @NotNull
    @NotBlank(message = "Необходимо указать имя")
    private String name;
    @NotNull
    @NotBlank(message = "Необходимо указать фамилию")
    private String surname;
    @NotNull
    private String secondName;
    @NotNull
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Телефонный номер должен начинаться с +7, затем - 10 цифр")
    private String phoneNumber;
    @NotNull
    @Email(message = "Email не корректен")
    private String email;
    private StudyGroupDTO studyGroup;
    @NotNull(message = "необходимо указать роль")
    private PersonRoleDTO personRole;
    @NotBlank
    @Size(min = 3, message = "Пароль должен соодержать хотя бы 3 символа")
    private String password;
    @NotBlank(message = "Необходимо указать логин")
    @NotNull
    private String login;
}
