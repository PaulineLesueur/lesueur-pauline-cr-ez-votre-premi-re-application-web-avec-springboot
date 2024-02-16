package com.openclassrooms.safetyNet.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class FireStation {

    private String address;

    private int station;

}
