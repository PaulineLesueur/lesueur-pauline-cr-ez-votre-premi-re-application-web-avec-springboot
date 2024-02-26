package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class FirestationService {

    @Autowired
    private FirestationRepository fireStationRepository;

}
