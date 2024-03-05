package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class MedicalrecordController {

    @Autowired
    private MedicalrecordService medicalrecordService;

    @GetMapping("/medicalrecords")
    public Iterable<Medicalrecord> getMedicalrecords() { return medicalrecordService.getMedicalrecords(); }

    @PostMapping("/medicalrecord")
    public Medicalrecord createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        return medicalrecordService.saveMedicalrecord(medicalrecord);
    }

    @PutMapping("/medicalrecord/{id}")
    public Medicalrecord updateMedicalrecord(@PathVariable("id") final Long id, @RequestBody Medicalrecord medicalrecord) {
        Optional<Medicalrecord> m = medicalrecordService.getMedicalrecordById(id);
        if(m.isPresent()) {
            Medicalrecord currentMedicalrecord = m.get();

            Date birthdate = currentMedicalrecord.getBirthdate();
            if(birthdate != null) {
                currentMedicalrecord.setBirthdate(birthdate);
            }

            List<String> medications = currentMedicalrecord.getMedications();
            if(medications != null) {
                currentMedicalrecord.setMedications(medications);
            }

            List<String> allergies = currentMedicalrecord.getAllergies();
            if(allergies != null) {
                currentMedicalrecord.setAllergies(allergies);
            }

            return currentMedicalrecord;

        } else {
            return null;
        }
    }

}
