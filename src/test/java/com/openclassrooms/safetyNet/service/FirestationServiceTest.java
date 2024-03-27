package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Firestation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.repository.FirestationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(FirestationService.class)
public class FirestationServiceTest {

    @Autowired
    private FirestationService firestationService;

    @MockBean
    private FirestationRepository firestationRepository;

    @MockBean
    private PersonService personService;

    @MockBean
    private MedicalrecordService medicalrecordService;

    private static final Firestation firestation = new Firestation(1L, "1509 Culver St", 3);

    public static List<Firestation> listOfFirestations = new ArrayList<>();

    static {
        listOfFirestations.add(new Firestation(1L,"1509 Culver St", 3));

        listOfFirestations.add(new Firestation(2L,"29 15th St", 2));
    }

    @Test
    public void testGetFirestationById() {
        when(firestationRepository.findById(any(Long.class))).thenReturn(Optional.of(firestation));
        Optional<Firestation> firestationFound = firestationService.getFirestationById(1L);
        assertEquals(firestation.getId(), firestationFound.get().getId());
    }

    @Test
    public void testCreateNewFirestation() {
        Firestation addFirestation = new Firestation(2L, "29 15th St", 2);
        when(firestationRepository.save(addFirestation)).thenReturn(addFirestation);
        Firestation newFirestation = firestationService.saveFirestation(addFirestation);
        assertEquals(addFirestation.getId(), newFirestation.getId());
    }

    @Test
    public void testUpdateFirestation() {
        Firestation firestationToUpdate = firestation;
        firestationToUpdate.setAddress("123 New Address");
        when(firestationRepository.save(firestationToUpdate)).thenReturn(firestationToUpdate);
        Firestation updatedFirestation = firestationService.saveFirestation(firestationToUpdate);
        assertEquals(firestationToUpdate.getAddress(), updatedFirestation.getAddress());
    }

    @Test
    public void testDeleteFirestation() {
        firestationService.deleteFireStationByAddress("1509 Culver St");
        verify(firestationRepository, times(1)).deleteByAddress("1509 Culver St");
    }

    @Test
    public void testGetStationNumberByAddress() {
        when(firestationRepository.findStationNumberByAddress(any(String.class))).thenReturn(3);
        Integer stationNumberFound = firestationService.getStationNumberByAddress("1509 Culver St");
        assertEquals(3, stationNumberFound);
    }

}
