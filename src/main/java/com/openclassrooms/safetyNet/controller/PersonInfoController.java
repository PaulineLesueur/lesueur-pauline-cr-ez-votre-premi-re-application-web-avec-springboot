package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.DTO.PersonInfoDTO;
import com.openclassrooms.safetyNet.DTO.PersonsByFirestationDTO;
import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonInfoController {
    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalrecordService medicalrecordService;

    @Autowired
    private AgeCalculator ageCalculator;

    @GetMapping("/personInfo")
    public PersonInfoDTO getPersonInfo(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname) {
        Optional<Person> person = personService.getPersonByLastnameAndFirstname(lastname, firstname);
        Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecordByLastnameFirstname(lastname, firstname);
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setFirstName(person.get().getFirstName());
        personInfoDTO.setLastName(person.get().getLastName());
        personInfoDTO.setAddress(person.get().getAddress());
        personInfoDTO.setAge(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()));
        personInfoDTO.setEmail(person.get().getEmail());
        personInfoDTO.setMedications(medicalrecord.get().getMedications());
        personInfoDTO.setAllergies(medicalrecord.get().getAllergies());

        return personInfoDTO;
    }

    @GetMapping("/childAlert")
    public ResponseEntity<List<PersonInfoDTO>> getChildAlert(@RequestParam("address") String address) {
        Iterable<Person> persons = personService.getPersonByAddress(address);
        List<PersonInfoDTO> childAlert = new ArrayList<>();

        for (Person person : persons) {
            PersonInfoDTO child = new PersonInfoDTO();
            Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecordByLastnameFirstname(person.getLastName(), person.getFirstName());

            if(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()) < 18) {
                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()));
                childAlert.add(child);
            }
        }

        if(childAlert.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(childAlert);

        } else {

            for(Person person : persons) {
                PersonInfoDTO adult = new PersonInfoDTO();
                Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecordByLastnameFirstname(person.getLastName(), person.getFirstName());

                if(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()) >= 18) {
                    adult.setFirstName(person.getFirstName());
                    adult.setLastName(person.getLastName());
                    childAlert.add(adult);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(childAlert);
        }
    }

    @GetMapping("/firestation")
    public ResponseEntity<PersonsByFirestationDTO> getPersonByFirestation(@RequestParam("stationNumber") Integer station) {
        PersonsByFirestationDTO personsByFirestationDTO = new PersonsByFirestationDTO();
        Iterable<Person> persons = personService.getPersonByStationNumber(station);
        Integer children = 0;
        Integer adults = 0;

        for(Person person : persons) {
            person.setId(null);
            person.setCity(null);
            person.setEmail(null);
            person.setZip(null);
            Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecordByLastnameFirstname(person.getLastName(), person.getFirstName());

            if(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()) < 18) {
                children++;
            } else {
                adults++;
            }
        }

        personsByFirestationDTO.setPersonList(persons);
        personsByFirestationDTO.setNumberOfChildren(children);
        personsByFirestationDTO.setNumberOfAdults(adults);

        return ResponseEntity.status(HttpStatus.OK).body(personsByFirestationDTO);
    }

}
