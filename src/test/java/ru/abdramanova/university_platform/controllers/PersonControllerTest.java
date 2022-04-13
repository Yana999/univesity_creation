package ru.abdramanova.university_platform.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.abdramanova.university_platform.entity.*;
import ru.abdramanova.university_platform.repositories.PersonRepository;
import ru.abdramanova.university_platform.repositories.PersonRoleRepository;
import static org.hamcrest.Matchers.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonRepository personRepository;
    @MockBean
    PersonRoleRepository personRoleRepository;

    private List<Person> initPersonData(){
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
        SubInGroup combInMvt2 = new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 20, 23, 59), combinatorics, mvt2, person4);
        SubInGroup englishInMvt2 = new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 20, 23, 59), english, mvt2, person5);

        Task task1 = new Task("Задача 1", "Решить задачи A - F контеста 1",
                LocalDateTime.of(2022, Month.APRIL, 11, 23, 59), combInMpi1);
        Task task2 = new Task("Задача 1", "Решить задачи A - F контеста 2",
                LocalDateTime.of(2022, Month.APRIL, 29, 23, 59), combInMvt2);
        Task task3 = new Task("Задача 2", "Решить задачи A - F контеста 3",
                LocalDateTime.of(2022, Month.MAY, 17, 13, 59), combInMpi1);
        Task task4 = new Task("Задача 3", "Решить задачи A - F контеста 4",
                LocalDateTime.of(2022, Month.APRIL, 21, 23, 50), combInMpi1);
        Task task5 = new Task("Задача 4", "Решить задачи A - F контеста 5",
                LocalDateTime.of(2022, Month.APRIL, 30, 21, 59), combInMpi1);
        Task task6 = new Task("Задание 1", "Выучить словарь за 2 семестр",
                LocalDateTime.of(2022, Month.MAY, 12, 23, 50), englishInMvt2);
        Task task7 = new Task("Проект", "1. Разбиться на комнады \n2. Выбрать тему проекта ",
                LocalDateTime.of(2022, Month.MAY, 30, 23, 59), englishInMvt2);

        Assessment assessment1 = new Assessment(56, person1, task1);
        Assessment assessment2 = new Assessment(71, person2, task1);
        Assessment assessment3 = new Assessment(51, person2, task2);
        Assessment assessment4 = new Assessment(23, person1, task3);
        Assessment assessment5 = new Assessment(90, person1, task4);
        Assessment assessment6 = new Assessment(78, person1, task5);
        Assessment assessment7 = new Assessment(88, person3, task3);
        Assessment assessment8 = new Assessment(11, person3, task4);

        return List.of(person1, person2, person3, person4);
    }

    @Test
    void allTeachers() throws Exception{
        PersonRole teacher = new PersonRole(1L, "teacher");
        teacher.setPeople(List.of(new Person("m1987654@edu.misis.ru", "135790","Бирина", "Венера", "Юрьевна", "89998887766", "BirinaV@mail.ru"),
                new Person("m1234567889@edu.misis.ru", "97531","Емельянова", "Татьяна", "Викторовна", "89121234345", "Emelyanove@mail.ru")));
        Mockito.when(personRoleRepository.findPersonRoleByNameIgnoreCase("teacher")).thenReturn(Optional.of(teacher));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/teachers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @Test
    void personById() {
    }

    @Test
    void toAccount() {
    }

    @Test
    void getStudentsBySurname() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void addPerson() {
    }

    @Test
    void testUpdatePerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void withAssessment() {
    }
}