package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.repository.MedicalrecordRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class MedicalrecordService {

    private MedicalrecordRepository medicalRecordRepository;

}
