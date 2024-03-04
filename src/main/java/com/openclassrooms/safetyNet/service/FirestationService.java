package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Firestation;
import com.openclassrooms.safetyNet.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class FirestationService {

    @Autowired
    private FirestationRepository fireStationRepository;

    public Iterable<Firestation> save(List<Firestation> firestations) {
        return fireStationRepository.saveAll(firestations);
    }
}
