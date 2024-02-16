package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.repository.FireStationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

}
