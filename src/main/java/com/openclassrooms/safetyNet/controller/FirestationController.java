package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

}
