package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonService.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private FirestationService firestationService;

    @MockBean
    private MedicalrecordService medicalrecordService;

    private static final Person person = new Person(1L,"John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

    public static List<Person> listOfPersons = new ArrayList<>();

    static {
        listOfPersons.add(new Person(1L,"John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));

        listOfPersons.add(new Person(2L,"Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"));

        listOfPersons.add(new Person(3L,"Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6514", "tenz@email.com"));
    }

    @Test
    public void testGetPersonByLastnameAndFirstname() {
        when(personRepository.findByLastnameAndFirstname(any(String.class), any(String.class))).thenReturn(Optional.of(person));
       Optional<Person> personFound = personService.getPersonByLastnameAndFirstname("John", "Boyd");
        assertEquals(person.getFirstName(), personFound.get().getFirstName());
    }

}
