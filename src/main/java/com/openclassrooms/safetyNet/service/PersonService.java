package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPersonByLastnameAndFirstname(String lastname, String firstname) {
        return personRepository.findByLastnameAndFirstname(lastname, firstname);
    }

    public Iterable<Person> getPersonByAddress(String address) {
        return personRepository.findByAddress(address);
    }

    public void deletePerson(String lastname, String firstname) {
        personRepository.delete(lastname, firstname);
    }

    public Iterable<Person> save(List<Person> persons) {
        return personRepository.saveAll(persons);
    }

    public Person savePerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

    public Iterable<String> getCommunityEmail(String city) {
        return personRepository.findCommunityEmail(city).orElseThrow(()->new NoSuchElementException("No mail found for this city"));
    }

    public Iterable<String> getPhoneByStationNumber(Integer station) {
        return personRepository.findPhoneByStationNumber(station).orElseThrow(()->new NoSuchElementException("No phone found for this station"));
    }

    public Iterable<Person> getPersonByStationNumber(Integer station) {
        return personRepository.findPersonByStationNumber(station);
    }

    public Iterable<Person> getPersonByStationNumberList(List<Integer> stations) {
        return personRepository.findPersonByStationNumberList(stations);
    }
}
