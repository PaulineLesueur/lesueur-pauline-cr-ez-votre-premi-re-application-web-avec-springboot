package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.Firestation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping("/firestations")
    public Iterable<Firestation> getFirestations() {
        return firestationService.getFirestations();
    }

    @PostMapping("/firestation")
    public Firestation createFirestation(Firestation firestation) {
        return firestationService.saveFirestation(firestation);
    }

    @PutMapping("/firestation/{id}")
    public Firestation updateFirestation(@PathVariable("id") final long id, @RequestBody Firestation firestation) {
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

            return firestationService.saveFirestation(currentFirestation);

        } else {
            return null;
        }
    }

    @DeleteMapping("/firestation/{station}")
    public void deleteFirestationByStationNumber(@PathVariable("station") Integer station) {
        firestationService.deleteFirestationByStationNumber(station);
    }

    @DeleteMapping("/firestation/{adress}")
    public void deleteFireStationByAdress(@PathVariable("adress") String adress) {
        firestationService.deleteFireStationByAdress(adress);
    }

    @GetMapping("/firestation")
    public Iterable<Person> getPersonByFirestationNumber(@RequestParam("stationNumber") Integer station) {
        return null;
    }

}
