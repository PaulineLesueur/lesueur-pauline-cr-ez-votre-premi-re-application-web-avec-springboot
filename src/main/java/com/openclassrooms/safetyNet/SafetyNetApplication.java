package com.openclassrooms.safetyNet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.DataFromJsonFile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}, scanBasePackages = {"com.openclassrooms.safetyNet.repository"})
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			ObjectMapper mapper =  new ObjectMapper();

			DataFromJsonFile data = mapper.readValue(new File("resources/data.json"), DataFromJsonFile.class);
			System.out.println(data);

		};
	}

}
