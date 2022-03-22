package ru.abdramanova.university_platform.mappers;

import org.mapstruct.Mapper;
import ru.abdramanova.university_platform.dto.*;
import ru.abdramanova.university_platform.entity.*;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface DTOMapper {

    AssessmentDTO assessmentToDTO(Assessment assessment);
    Assessment assessmentDTOtoAssessment(AssessmentDTO assessmentDTO);
    List<Assessment> assessmentDTOListToAssessmentList (List<AssessmentDTO> assessments);
    List<AssessmentDTO> assessmentListToDTOList(List<Assessment> assessments);

    ControlFormDTO controlFormToDTO(ControlFormDict controlFormDict);
    ControlFormDict controlFormDTOtoControlForm(ControlFormDTO controlFormDTO);

    MaterialDTO materialToDTO(Material material);
    Material materialDTOtoMaterial(MaterialDTO materialDTO);

    PersonSimpleDTO simplePersonToDTO(Person person);
    Person simplePersonDTOtoPerson(PersonSimpleDTO personSimpleDTO);
    List<PersonSimpleDTO> simplePersonListToDTO(List<Person> people);

    PersonShortDTO teacherToDTO (Person person);
    Person teacherDTOtoPerson(PersonShortDTO simpleUserDTO);

    Person personDTOtoPerson(PersonDTO personDTO);
    PersonDTO personToPersonDTO(Person person);

    StudyGroupDTO studyGroupToDTO(StudyGroup studyGroup);
    StudyGroup groupDTOtoStudyGroup(StudyGroupDTO studyGroupDTO);

    SubInGroupDTO subInGroupToDTO(SubInGroup subInGroup);
    SubInGroup subInGroupDTOtoSubInGroup(SubInGroupDTO subInGroupDTO);

    SubjectDTO subjectToDTO(Subject subject);
    Subject subjectDTOtoSubject(SubjectDTO subjectDTO);

    TaskDTO taskToDTO(Task task);
    Task taskDTOtoTask(TaskDTO taskDTO);
    List<TaskDTO> taskListToDTO(List<Task> tasks);

    List<TaskWithAssessmentsDTO> taskWithAssessToDTO(List<Task> tasks);
    List<Task> taskWithAssessDTOtoTask(List<TaskWithAssessmentsDTO> taskWithAssessmentsDTOS);

    FullTaskDTO fullTaskToDTO (Task task);
    Task fullTaskDTOtoTask (FullTaskDTO fullTaskDTO);

    List<MaterialUrlDTO> materialListUrlToDTO (List<Material> material);
    MaterialUrlDTO materialUrlToDTO (Material material);
    Material materialUrlDTOtoMaterial (MaterialUrlDTO materialUrlDTO);
}
