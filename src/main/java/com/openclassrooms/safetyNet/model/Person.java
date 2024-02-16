package com.openclassrooms.safetyNet.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Person {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private double zip;

    private String phone;

    private String email;

}
