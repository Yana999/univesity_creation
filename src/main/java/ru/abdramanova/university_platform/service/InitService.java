package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.*;
import ru.abdramanova.university_platform.repositories.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;


@Service
public class InitService implements ApplicationRunner {

    private final AssessmentRepository assessmentRepository;
    private final MaterialRepository materialRepository;
    private final PersonRepository personRepository;
    private final PersonRoleRepository personRoleRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final SubInGroupRepository subInGroupRepository;
    private final SubjectRepository subjectRepository;
    private final TaskRepository taskRepository;
    private final ControlFormDictRepository controlFormDictRepository;

    @Autowired
    public InitService(AssessmentRepository assessmentRepository, ControlFormDictRepository controlFormDictRepository, MaterialRepository materialRepository, PersonRepository personRepository, PersonRoleRepository personRoleRepository, StudyGroupRepository studyGroupRepository, SubInGroupRepository subInGroupRepository, SubjectRepository subjectRepository, TaskRepository taskRepository) {
        this.assessmentRepository = assessmentRepository;
        this.materialRepository = materialRepository;
        this.personRepository = personRepository;
        this.personRoleRepository = personRoleRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.subInGroupRepository = subInGroupRepository;
        this.subjectRepository = subjectRepository;
        this.taskRepository = taskRepository;
        this.controlFormDictRepository = controlFormDictRepository;
    }

    public void initDB() {
            ControlFormDict test = new ControlFormDict("зачет");
            ControlFormDict exam = new ControlFormDict("экзамен");
            ControlFormDict test2 = new ControlFormDict("зачет с оценкой");
            Subject combinatorics = new Subject("Комбинаторика", exam);
            Subject english = new Subject("Английский", test);

            PersonRole teacher = new PersonRole("teacher");
            PersonRole student = new PersonRole("student");
            PersonRole admin = new PersonRole("admin");

            StudyGroup mpi1 = new StudyGroup("МПИ-21-1-7");
            StudyGroup mvt2 = new StudyGroup("МИВТ-21-2-4");

            Person person1 = new Person("m1234567@edu.misis.ru", "123456", "Абдраманова", "Яна", "Расимовна", "89628569064", "Abdramanova.yana@yandex.ru", mpi1, student);
            Person person2 = new Person("m1122334@edu.misis.ru", "112233", "Новикова", "Полина", "Сергеевна", "89876543211", "PS1234@yandex.ru", mvt2, student);
            Person person3 = new Person("m1702203@edu.misis.ru", "654321", "Битаров", "Костантин", "Эльбрусович", "89112223344", "TheNoneMan@gmai.com", mpi1, student);
            Person person4 = new Person("m1987654@edu.misis.ru", "135790","Бирина", "Венера", "Юрьевна", "89998887766", "BirinaV@mail.ru", teacher);
            Person person5 = new Person("m1234567889@edu.misis.ru", "97531","Емельянова", "Татьяна", "Викторовна", "89121234345", "Emelyanove@mail.ru", teacher);
            Person person8 = new Person("m123@edu.misis.ru", "123","Абдраманова", "Анастасия", "Расимовна", "89111111111", "Abdramanova@mail.ru", admin);

            SubInGroup combInMpi1 = new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 23, 23, 59), combinatorics, mpi1, person4);
            SubInGroup combInMvt2 = new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 20, 23, 59), english, mvt2, person5);

            Task task1 = new Task(new TaskKey("Задача 1"), "Решить задачи A - F контеста 1",
                    LocalDateTime.of(2022, Month.MARCH, 11, 23, 59), combInMpi1);
            Task task2 = new Task(new TaskKey("Задача 1"), "Решить задачи A - F контеста 1",
                    LocalDateTime.of(2022, Month.MARCH, 15, 23, 59), combInMvt2);
            Task task3 = new Task(new TaskKey("Задача 2"), "Решить задачи A - F контеста 1",
                    LocalDateTime.of(2022, Month.MARCH, 17, 13, 59), combInMpi1);
            Task task4 = new Task(new TaskKey("Задача 3"), "Решить задачи A - F контеста 1",
                    LocalDateTime.of(2022, Month.MARCH, 21, 23, 50), combInMpi1);
            Task task5 = new Task(new TaskKey("Задача 4"), "Решить задачи A - F контеста 1",
                    LocalDateTime.of(2022, Month.MARCH, 30, 21, 59), combInMpi1);

            Assessment assessment1 = new Assessment(56, person1, task1);
            Assessment assessment2 = new Assessment(71, person2, task1);
            Assessment assessment3 = new Assessment(51, person2, task2);
            Assessment assessment4 = new Assessment(23, person1, task3);
            Assessment assessment5 = new Assessment(90, person1, task4);
            Assessment assessment6 = new Assessment(78, person1, task5);
            Assessment assessment7 = new Assessment(88, person3, task3);
            Assessment assessment8 = new Assessment(11, person3, task4);



            controlFormDictRepository.saveAll(List.of(exam, test, test2));
            personRoleRepository.saveAll(List.of(teacher,student,admin));
            studyGroupRepository.saveAll(List.of(mpi1, mvt2));
            personRepository.saveAll(List.of(person1, person2,person3, person4, person5));
            subInGroupRepository.saveAll(List.of(combInMpi1,combInMvt2));
            taskRepository.saveAllAndFlush(List.of(task1, task2,task3,task4, task5));
        assessmentRepository.saveAll(List.of(assessment1,assessment2, assessment3, assessment4, assessment5,assessment6, assessment7, assessment8));
            try{
                byte[] materialFile =  Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("files/Stat 1.pdf")).readAllBytes();
                Material material1 = new Material("To task 1", MediaType.APPLICATION_PDF_VALUE, Long.valueOf(materialFile.length),  materialFile, task1);
                materialRepository.save(material1);

                byte[] materialFile2 = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("files/photo2022.jpg")).readAllBytes();
                Material material2 = new Material("To task 1", MediaType.IMAGE_JPEG_VALUE, Long.valueOf(materialFile2.length), materialFile2, task1);
                materialRepository.save(material2);
            }catch (IOException e){
                System.out.println("Cannot read the file");
            }

    }

    @Override
    public void run(ApplicationArguments args){
        initDB();
    }

    @EventListener
    public void handleContextStarted(ContextStartedEvent event) {
        Subject quantum = new Subject("Квантовая информатика", controlFormDictRepository.findById((short)2).get());
        Person person6 = new Person("m214365@edu.misis.ru", "6090999","Арсентьев", "Александр", "Андреевич", "89776090228", "m1605456@edu.misis.ru", studyGroupRepository.findByName("МПИ-21-1-7").get(0), personRoleRepository.findPersonRoleByNameIgnoreCase("student").get());
        Person person7 = new Person("m111111@edu.misis.ru", "112233anya", "Бердичевская", "Анна", "Григорьевна", "89257034136", "m1704475@edu.misis.ru", studyGroupRepository.findByName("МПИ-21-1-7").get(0), personRoleRepository.findPersonRoleByNameIgnoreCase("student").get());
        personRepository.save(person6);
        personRepository.save(person7);
        subjectRepository.save(quantum);
    }

}
