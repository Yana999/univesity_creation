package ru.abdramanova.university_platform;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.abdramanova.university_platform.entity.Person;
import ru.abdramanova.university_platform.service.PersonService;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonDetailVerification {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;


    @Test
    public void surnameValidation() throws Exception{

        Mockito.when(service.savePerson(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Яна\"," +
                        "\"surname\":\"абдраманова\"," +
                        "\"secondName\":\"Расимовна\"," +
                        "\"phoneNumber\":\"89628569064\"," +
                        "\"email\":\"Abdramanova.yana@yandex.ru\"," +
                                "\"personRole\":{" +
                        "\"id\" : \"2\" , " +
                        "\"name\" : \"student\"}" +
                        "\"login\":\"aabbddnn\"," +
                        "\"password\":\"123456\","))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
