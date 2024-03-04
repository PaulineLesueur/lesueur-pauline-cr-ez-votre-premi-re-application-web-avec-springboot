package com.openclassrooms.safetyNet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.DataFromJsonFile;
import com.openclassrooms.safetyNet.service.FirestationService;
import com.openclassrooms.safetyNet.service.MedicalrecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication()
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FirestationService firestationService, MedicalrecordService medicalrecordService, PersonService personService) {
		return args -> {

			ObjectMapper mapper =  new ObjectMapper();
			TypeReference<DataFromJsonFile> typeReference = new TypeReference<DataFromJsonFile>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
			try {
				DataFromJsonFile dataFromJsonFile = mapper.readValue(inputStream, typeReference);
				personService.save(dataFromJsonFile.getPersons());
				firestationService.save(dataFromJsonFile.getFirestations());
				medicalrecordService.save(dataFromJsonFile.getMedicalrecords());
			} catch (IOException e){
				System.out.println("Unable to save data: " + e.getMessage());
			}

		};
	}

}
