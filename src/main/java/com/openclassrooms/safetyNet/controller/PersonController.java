package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @PutMapping("/person/{lastname}/{firstname}")
    public Person updatePerson(@PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname, @RequestBody Person person) {
        Optional<Person> p = personService.getPersonbyLastnameFirstname(lastname, firstname);
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

            return personService.savePerson(currentPerson);

        } else {
            return null;
        }
    }

    @DeleteMapping("/person/{lastname}/{firstname}")
    public void deletePerson(@PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname) {
        personService.deletePerson(lastname, firstname);
    }

}
