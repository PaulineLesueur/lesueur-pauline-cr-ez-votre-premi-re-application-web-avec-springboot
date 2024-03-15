package com.openclassrooms.safetyNet.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PersonInfoDTO {

    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

}
