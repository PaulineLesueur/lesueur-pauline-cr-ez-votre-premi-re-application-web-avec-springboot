package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Firestation;
import com.openclassrooms.safetyNet.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class FirestationService {

    @Autowired
    private FirestationRepository fireStationRepository;

    public Optional<Firestation> getFirestationById(final Long id) {
        return fireStationRepository.findById(id);
    }

    public Integer getStationNumberByAddress(String address) {
        return fireStationRepository.findStationNumberByAddress(address);
    }

    public Iterable<Firestation> save(List<Firestation> firestations) {
        return fireStationRepository.saveAll(firestations);
    }

    public Firestation saveFirestation(Firestation firestation) {
        Firestation savedFirestation = fireStationRepository.save(firestation);
        return savedFirestation;
    }

    public void deleteFireStationByAddress(String adress) {
        fireStationRepository.deleteByAddress(adress);
    }
}
