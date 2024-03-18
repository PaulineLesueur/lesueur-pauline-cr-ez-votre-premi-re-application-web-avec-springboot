package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class MedicalrecordController {

    @Autowired
    private MedicalrecordService medicalrecordService;

    @PostMapping("/medicalrecord")
    public Medicalrecord createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        return medicalrecordService.saveMedicalrecord(medicalrecord);
    }

    @PutMapping("/medicalrecord/{lastname}/{firstname}")
    public Medicalrecord updateMedicalrecord(@PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname, @RequestBody Medicalrecord medicalrecord) {
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

            return medicalrecordService.saveMedicalrecord(currentMedicalrecord);

        } else {
            return null;
        }
    }

    @DeleteMapping("/medicalrecord")
    @Transactional
    public void deleteMedicalrecord(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname) {
        medicalrecordService.deleteMedicalrecord(lastname, firstname);
    }

}
