package com.openclassrooms.safetyNet.model;

import lombok.Data;

import java.util.List;

@Data
public class DataFromJsonFile {

    private List<Person> persons;

    private List<Firestation> firestations;

    private List<Medicalrecord> medicalrecords;

    public DataFromJsonFile() {

    }
}
