package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.FirestationService;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static javax.swing.UIManager.put;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PersonService personService;

    @MockBean
    FirestationService firestationService;

    @MockBean
    MedicalrecordService medicalrecordService;

    private static Person person = new Person(1L,"John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

    @Test
    public void testCreatePerson() throws Exception {
        when(personService.savePerson(person)).thenReturn(person);
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    @Test
    public void testDeleteExistingPerson() throws Exception {
        when(personService.getPersonByLastnameAndFirstname(any(String.class), any(String.class))).thenReturn(Optional.of(person));
        mockMvc.perform(delete("/person")
                    .param("lastname", person.getLastName())
                    .param("firstname", person.getFirstName()))
                .andExpect(status().isOk())
                .andExpect(content().string("John Boyd has been deleted successfully"));
    }

    @Test
    public void testDeleteNotFoundPerson() throws Exception {
        when(personService.getPersonByLastnameAndFirstname(any(String.class), any(String.class))).thenReturn(Optional.empty());
        mockMvc.perform(delete("/person")
                    .param("lastname", person.getLastName())
                    .param("firstname", person.getFirstName()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("John Boyd not found in database"));
    }

    @Test
    public void testGetCommunityEmail() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPhoneNumberByStation() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isOk());
    }

}
