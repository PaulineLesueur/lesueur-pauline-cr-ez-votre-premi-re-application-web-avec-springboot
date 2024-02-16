package com.openclassrooms.safetyNet.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.Date;

@Data
@Entity
public class MedicalRecord {

    private String firstName;

    private String lastName;

    private Date birthdate;

    private Array medications;

    private Array allergies;

}
