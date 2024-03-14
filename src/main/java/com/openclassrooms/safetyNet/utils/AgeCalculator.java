package com.openclassrooms.safetyNet.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class AgeCalculator {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public int ageCalculator(String birthdate) {

        LocalDate birthDate = LocalDate.parse(birthdate, dateTimeFormatter);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        return age;
    }

}
