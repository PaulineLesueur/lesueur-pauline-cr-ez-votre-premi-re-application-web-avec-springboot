package com.openclassrooms.safetyNet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "firestations")
public class Firestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Integer station;

}
