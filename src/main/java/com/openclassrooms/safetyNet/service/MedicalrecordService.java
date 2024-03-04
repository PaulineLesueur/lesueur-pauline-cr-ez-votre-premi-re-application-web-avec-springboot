package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.repository.MedicalrecordRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class MedicalrecordService {

    @Autowired
    private MedicalrecordRepository medicalRecordRepository;

    public Iterable<Medicalrecord> save(List<Medicalrecord> medicalrecords) {
        return medicalRecordRepository.saveAll(medicalrecords);
    }
}
