package ru.abdramanova.university_platform;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.abdramanova.university_platform.repositories.TaskRepository;
import ru.abdramanova.university_platform.service.*;


@SpringBootApplication
public class UnivesityPlatformApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger("JCG");

    private SubjectService subjectService;

    private PersonService personService;

    private StudyGroupService studyGroupService;

    private TaskService taskService;

    private AssessmentService assessmentService;

    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setStudyGroupService(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnivesityPlatformApplication.class, args);
    }

    @Override
    public void run(String... strings) {

        subjectService.initControlForm();
        subjectService.initSubjects();
        personService.initPersonRole();
        studyGroupService.initGroup();
        personService.initPerson();
        subjectService.addInfo();
        taskService.initTasks();
        assessmentService.initAssessments();
    }
}
