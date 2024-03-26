package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import com.openclassrooms.safetyNet.repository.MedicalrecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(MedicalrecordService.class)
public class MedicalrecordServiceTest {
    @Autowired
    private MedicalrecordService medicalrecordService;

    @MockBean
    private MedicalrecordRepository medicalrecordRepository;

    @MockBean
    private PersonService personService;

    @MockBean
    private FirestationService firestationService;

    private static final Medicalrecord medicalrecord = new Medicalrecord(1L, "John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));

    public static List<Medicalrecord> listOfMedicalrecords = new ArrayList<>();

    static {
        listOfMedicalrecords.add(new Medicalrecord(1L, "John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan")));

        listOfMedicalrecords.add(new Medicalrecord(2L, "Jacob", "Boyd", "03/06/1989", List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), null));

        listOfMedicalrecords.add(new Medicalrecord(3L, "Tenley", "Boyd", "02/18/2012", null, List.of("peanut")));
    }

    @Test
    public void testGetMedicalrecordsByLastnameAndFirstname() {
        when(medicalrecordRepository.findByLastnameAndFirstname(any(String.class), any(String.class))).thenReturn(Optional.of(medicalrecord));
        Optional<Medicalrecord> medicalrecordFound = medicalrecordService.getMedicalrecordByLastnameFirstname("John", "Boyd");
        assertEquals(medicalrecord.getFirstName(), medicalrecordFound.get().getFirstName());
    }

}
