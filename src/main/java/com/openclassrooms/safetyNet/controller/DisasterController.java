package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.DTO.FireDTO;
import com.openclassrooms.safetyNet.DTO.PersonInfoDTO;
import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.FirestationService;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class DisasterController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalrecordService medicalrecordService;

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private AgeCalculator ageCalculator;

    @GetMapping("/fire")
    public ResponseEntity<FireDTO> getPersonByAddress(@RequestParam("address") String address) {
        FireDTO fireDTO = new FireDTO();
        Iterable<Person> persons = personService.getPersonByAddress(address);
        List<PersonInfoDTO> personInfoDTOList = new ArrayList<>();

        for(Person person : persons) {
            PersonInfoDTO personInfoDTO = new PersonInfoDTO();
            Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecordByLastnameFirstname(person.getLastName(), person.getFirstName());

            personInfoDTO.setLastName(person.getLastName());
            personInfoDTO.setFirstName(person.getFirstName());
            personInfoDTO.setPhone(person.getPhone());
            personInfoDTO.setAge(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()));
            personInfoDTO.setMedications(medicalrecord.get().getMedications());
            personInfoDTO.setAllergies(medicalrecord.get().getAllergies());
            personInfoDTOList.add(personInfoDTO);

        }

        fireDTO.setPersonInfoDTOList(personInfoDTOList);
        fireDTO.setStationNumber(firestationService.getStationNumberByAddress(address));

        return ResponseEntity.status(HttpStatus.OK).body(fireDTO);
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<List<LinkedHashMap<String, Object>>> getPersonByStations(@RequestParam("stations") List<Integer> stations) {
        List<LinkedHashMap<String, Object>> personInfoDTOList = new ArrayList<>();
        Iterable<Person> persons = personService.getPersonByStationNumberList(stations);

        for(Person person : persons) {
            PersonInfoDTO personInfoDTO = new PersonInfoDTO();
            Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecordByLastnameFirstname(person.getLastName(), person.getFirstName());

            personInfoDTO.setFirstName(person.getFirstName());
            personInfoDTO.setLastName(person.getLastName());
            personInfoDTO.setAddress(person.getAddress());
            personInfoDTO.setPhone(person.getPhone());
            personInfoDTO.setAge(ageCalculator.ageCalculator(medicalrecord.get().getBirthdate()));
            personInfoDTO.setMedications(medicalrecord.get().getMedications());
            personInfoDTO.setAllergies(medicalrecord.get().getAllergies());

            LinkedHashMap<String, Object> personInfoMap = new LinkedHashMap<>();
            personInfoMap.put("firstName", personInfoDTO.getFirstName());
            personInfoMap.put("lastName", personInfoDTO.getLastName());
            personInfoMap.put("medications", personInfoDTO.getMedications());
            personInfoMap.put("allergies", personInfoDTO.getAllergies());
            personInfoMap.put("address", personInfoDTO.getAddress());
            personInfoMap.put("phone", personInfoDTO.getPhone());
            personInfoMap.put("age", personInfoDTO.getAge());

            personInfoDTOList.add(personInfoMap);
        }

        return ResponseEntity.status(HttpStatus.OK).body(personInfoDTOList);
    }

}
