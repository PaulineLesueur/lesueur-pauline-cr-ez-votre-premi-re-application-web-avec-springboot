package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> save(List<Person> persons) {
        return personRepository.saveAll(persons);
    }
}
