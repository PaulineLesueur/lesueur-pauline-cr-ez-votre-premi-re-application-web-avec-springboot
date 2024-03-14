package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.DTO.PersonInfoDTO;
import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Optional<Person> person = personService.getPersonbyLastnameFirstname(lastname, firstname);
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

}
