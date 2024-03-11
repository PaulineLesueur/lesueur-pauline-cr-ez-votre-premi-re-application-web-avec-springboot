package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.repository.MedicalrecordRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class MedicalrecordService {

    @Autowired
    private MedicalrecordRepository medicalRecordRepository;

    public Iterable<Medicalrecord> getMedicalrecords() {
        return medicalRecordRepository.findAll();
    }

    public Optional<Medicalrecord> getMedicalrecordByLastnameFirstname(String lastname, String firstname) {
        return medicalRecordRepository.findByLastnameAndFirstname(lastname, firstname);
    }

    public Iterable<Medicalrecord> save(List<Medicalrecord> medicalrecords) {
        return medicalRecordRepository.saveAll(medicalrecords);
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord) {
        Medicalrecord savedMedicalRecord = medicalRecordRepository.save(medicalrecord);
        return savedMedicalRecord;
    }

    public void deleteMedicalrecord(String lastname, String firstname) {
        medicalRecordRepository.delete(lastname, firstname);
    }
}
