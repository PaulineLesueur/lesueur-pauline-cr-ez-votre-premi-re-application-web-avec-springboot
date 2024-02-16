package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.repository.MedicalRecordRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;

}
