package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class MedicalrecordController {

    @Autowired
    private MedicalrecordService medicalrecordService;

    @PostMapping("/medicalrecord")
    public ResponseEntity<Medicalrecord> createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        Medicalrecord createdMedicalrecord = medicalrecordService.saveMedicalrecord(medicalrecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalrecord);
    }

    @PutMapping("/medicalrecord/{lastname}/{firstname}")
    public ResponseEntity<?> updateMedicalrecord(@PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname, @RequestBody Medicalrecord medicalrecord) {
        Optional<Medicalrecord> m = medicalrecordService.getMedicalrecordByLastnameFirstname(lastname, firstname);
        if(m.isPresent()) {
            Medicalrecord currentMedicalrecord = m.get();

            String birthdate = medicalrecord.getBirthdate();
            if(birthdate != null) {
                currentMedicalrecord.setBirthdate(birthdate);
            }

            List<String> medications = medicalrecord.getMedications();
            if(medications != null) {
                currentMedicalrecord.setMedications(medications);
            }

            List<String> allergies = medicalrecord.getAllergies();
            if(allergies != null) {
                currentMedicalrecord.setAllergies(allergies);
            }

            Medicalrecord updatedMedicalrecord = medicalrecordService.saveMedicalrecord(currentMedicalrecord);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMedicalrecord);

        } else {
            String notFoundMessage = firstname + " " + lastname + " medicalrecord not found in database";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

    @DeleteMapping("/medicalrecord")
    @Transactional
    public ResponseEntity<?> deleteMedicalrecord(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname) {
        Optional<Medicalrecord> m = medicalrecordService.getMedicalrecordByLastnameFirstname(lastname, firstname);
        if(m.isPresent()) {
            medicalrecordService.deleteMedicalrecord(lastname, firstname);
            String successMessage = firstname + " " + lastname + " medicalrecord has been deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } else {
            String notFoundMessage = firstname + " " + lastname + " not found in database";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

}
