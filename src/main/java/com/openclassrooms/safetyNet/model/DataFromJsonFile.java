package com.openclassrooms.safetyNet.model;

import lombok.Data;

import java.util.List;

@Data
public class DataFromJsonFile {

    private List<FireStation> fireStations;

    private List<MedicalRecord> medicalRecords;

    private List<Person> persons;

    public DataFromJsonFile() {

    }
}
