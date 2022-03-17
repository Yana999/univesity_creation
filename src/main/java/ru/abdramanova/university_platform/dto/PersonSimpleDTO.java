package ru.abdramanova.university_platform.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonSimpleDTO {

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

}
