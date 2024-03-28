package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Firestation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.FirestationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @PostMapping("/firestation")
    public ResponseEntity<Firestation> createFirestation(Firestation firestation) {
        Firestation createdFirestation = firestationService.saveFirestation(firestation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFirestation);
    }

    @PutMapping("/firestation/{id}")
    public ResponseEntity<?> updateFirestation(@PathVariable("id") final long id, @RequestBody Firestation firestation) {
        Optional<Firestation> f = firestationService.getFirestationById(id);
        if (f.isPresent()) {
            Firestation currentFirestation = f.get();

            String adress = firestation.getAddress();
            if(adress != null) {
                currentFirestation.setAddress(adress);
            }

            Integer station = firestation.getStation();
            if(station != null) {
                currentFirestation.setStation(station);
            }

            Firestation updatedFirestation = firestationService.saveFirestation(currentFirestation);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFirestation);

        } else {
                String notFoundMessage = "firestation not found in database";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

    @DeleteMapping("/firestation")
    @Transactional
    public void deleteFireStationByAddress(@RequestParam("address") String address) {
        firestationService.deleteFireStationByAddress(address);
    }

}
