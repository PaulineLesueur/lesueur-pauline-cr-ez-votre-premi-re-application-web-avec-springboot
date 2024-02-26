package com.openclassrooms.safetyNet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.DataFromJsonFile;
import com.openclassrooms.safetyNet.repository.FireStationRepository;
import com.openclassrooms.safetyNet.repository.MedicalRecordRepository;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import com.openclassrooms.safetyNet.service.FireStationService;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

@SpringBootApplication()
@ComponentScan(basePackages = "com.openclassrooms.safetyNet.*")
@EntityScan("com.openclassrooms.safetyNet.*")
@EnableJpaRepositories(basePackages = "com.openclassrooms.safetyNet.repository")
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository, PersonRepository personRepository) {
		return args -> {

			ObjectMapper mapper =  new ObjectMapper();

			DataFromJsonFile data = mapper.readValue(new File("src/main/resources/data.json"), DataFromJsonFile.class);
			fireStationRepository.saveAll(data.getFirestations());
			medicalRecordRepository.saveAll(data.getMedicalrecords());
			personRepository.saveAll(data.getPersons());

		};
	}

}
