package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    public static List<String> listOfEmails = new ArrayList<>();

    static {
        listOfEmails.add("jaboyd@email.com");
        listOfEmails.add("drk@email.com");
        listOfEmails.add("tenz@email.com");
    }

    @Test
    public void testGetPersonByLastnameAndFirstname() {
        when(personRepository.findByLastnameAndFirstname(any(String.class), any(String.class))).thenReturn(Optional.of(person));
       Optional<Person> personFound = personService.getPersonByLastnameAndFirstname("John", "Boyd");
        assertEquals(person.getFirstName(), personFound.get().getFirstName());
    }


    @Test
    public void testCreateNewPerson() {
        Person addPerson = new Person(2L,"Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com");
        when(personRepository.save(addPerson)).thenReturn(addPerson);
        Person newPerson = personService.savePerson(addPerson);
        assertEquals(addPerson.getFirstName(), newPerson.getFirstName());
    }

    @Test
    public void testUpdatePerson() {
        Person personToUpdate = person;
        personToUpdate.setCity("NewCity");
        when(personRepository.save(personToUpdate)).thenReturn(personToUpdate);
        Person updatedPerson = personService.savePerson(personToUpdate);
        assertEquals(personToUpdate.getCity(), updatedPerson.getCity());
    }

    @Test
    public void testDeletePerson() {
        when(personRepository.findByLastnameAndFirstname(any(String.class), any(String.class))).thenReturn(Optional.of(person));
        personService.deletePerson("Boyd", "John");
        verify(personRepository, times(1)).delete("Boyd", "John");
    }

    @Test
    public void testGetPersonByAddress() {
        when(personRepository.findByAddress(any(String.class))).thenReturn(listOfPersons);
        Iterable<Person> personsFound = personService.getPersonByAddress("1509 Culver St");
        assertEquals(listOfPersons, personsFound);
    }

    @Test
    public void testGetCommunityEmail() {
        when(personRepository.findCommunityEmail(any(String.class))).thenReturn(Optional.ofNullable(listOfEmails));
        Iterable<String> emailsFound = personService.getCommunityEmail("Culver");
        assertEquals(listOfEmails, emailsFound);
    }

}
