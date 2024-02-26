package com.openclassrooms.safetyNet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.DataFromJsonFile;
import com.openclassrooms.safetyNet.repository.FirestationRepository;
import com.openclassrooms.safetyNet.repository.MedicalrecordRepository;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.File;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.openclassrooms.safetyNet.*")
@EntityScan("com.openclassrooms.safetyNet.*")
@EnableJpaRepositories(basePackages = "com.openclassrooms.safetyNet.repository")
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FirestationRepository fireStationRepository, MedicalrecordRepository medicalRecordRepository, PersonRepository personRepository) {
		return args -> {

			ObjectMapper mapper =  new ObjectMapper();

			DataFromJsonFile data = mapper.readValue(new File("src/main/resources/data.json"), DataFromJsonFile.class);
			fireStationRepository.saveAll(data.getFirestations());
			medicalRecordRepository.saveAll(data.getMedicalrecords());
			personRepository.saveAll(data.getPersons());

		};
	}

	// Manual configuration to JPA, fix error "required bean named entityManagerFactory" //
	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		return sessionFactory;
	}

}
