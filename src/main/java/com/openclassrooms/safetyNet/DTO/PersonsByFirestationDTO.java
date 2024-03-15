package com.openclassrooms.safetyNet.DTO;

import com.openclassrooms.safetyNet.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class PersonsByFirestationDTO {

    private Iterable<Person> personList;
    private Integer numberOfChildren;
    private Integer numberOfAdults;

}
