package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping("/person/{lastname}/{firstname}")
    public ResponseEntity<?> updatePerson(@PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname, @RequestBody Person person) {
        Optional<Person> p = personService.getPersonByLastnameAndFirstname(lastname, firstname);
        if(p.isPresent()) {
            Person currentPerson = p.get();

            String adress = person.getAddress();
            if(adress != null) {
                currentPerson.setAddress(adress);
            }

            String city = person.getCity();
            if(city !=null) {
                currentPerson.setCity(city);
            }

            String zip = person.getZip();
            if(zip != null) {
                currentPerson.setZip(zip);
            }

            String phone = person.getPhone();
            if(phone != null) {
                currentPerson.setPhone(phone);
            }

            String email = person.getEmail();
            if(email != null) {
                currentPerson.setEmail(email);
            }

            Person updatedPerson = personService.savePerson(currentPerson);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);

        } else {
            String notFoundMessage = firstname + " " + lastname + " not found in database";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

    @DeleteMapping("/person")
    @Transactional
    public ResponseEntity<?> deletePerson(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname) {
        Optional<Person> p = personService.getPersonByLastnameAndFirstname(lastname, firstname);
        if(p.isPresent()) {
            personService.deletePerson(lastname, firstname);
            String successMessage = firstname + " " + lastname + " has been deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } else {
            String notFoundMessage = firstname + " " + lastname + " not found in database";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

    @GetMapping("/communityEmail")
    public ResponseEntity<?> getCommunityEmail(@RequestParam("city") String city) {
        Iterable<String> communityEmail = personService.getCommunityEmail(city);
        return ResponseEntity.status(HttpStatus.OK).body(communityEmail);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<?> getPhoneByStationNumber(@RequestParam("firestation") Integer station) {
        Iterable<String> phoneNumbers = personService.getPhoneByStationNumber(station);
        return ResponseEntity.status(HttpStatus.OK).body(phoneNumbers);
    }

}
