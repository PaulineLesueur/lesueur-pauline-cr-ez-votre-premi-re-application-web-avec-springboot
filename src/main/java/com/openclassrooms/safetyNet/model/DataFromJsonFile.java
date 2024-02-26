package com.openclassrooms.safetyNet.model;

import lombok.Data;

import java.util.List;

@Data
public class DataFromJsonFile {

    private List<Person> persons;

    private List<FireStation> firestations;

    private List<MedicalRecord> medicalrecords;

    public DataFromJsonFile() {

    }
}
