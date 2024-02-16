package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.repository.PersonRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class PersonService {

    private PersonRepository personRepository;

}
