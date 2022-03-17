package ru.abdramanova.university_platform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDTO {

    private String id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String secondName;
    @NotNull
    private String phoneNumber;
    @NotNull
    @Email
    private String email;
    @NotNull
    private StudyGroupDTO studyGroup;
    @NotNull
    private PersonRoleDTO personRole;
    @NotBlank
    @Size(min = 3)
    private String password;
    @NotBlank
    private String login;
}
