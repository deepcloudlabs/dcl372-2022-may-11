package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// -Dspring.profiles.active=dev
// application-dev.properties
@SpringBootApplication
public class StudySpringProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringProfileApplication.class, args);
	}

}
