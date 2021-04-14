package de.iplytics.codingchallenge_backend_webapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodingchallengeBackendWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingchallengeBackendWebappApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
