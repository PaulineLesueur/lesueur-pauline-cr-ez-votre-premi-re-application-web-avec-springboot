package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

}
