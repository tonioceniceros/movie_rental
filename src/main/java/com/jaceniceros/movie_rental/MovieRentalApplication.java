package com.jaceniceros.movie_rental;  // Defines the package where this class belongs

import org.springframework.boot.SpringApplication;  // Imports Spring Boot's application runner utility
import org.springframework.boot.autoconfigure.SpringBootApplication;  // Imports annotation to mark this as a Spring Boot app

@SpringBootApplication  // Marks this class as the main Spring Boot application (enables auto-configuration and component scanning)
public class MovieRentalApplication {  // Defines the main application class for the Movie Rental project

	public static void main(String[] args) {  // Main method — entry point of the Java application
		SpringApplication.run(MovieRentalApplication.class, args);  // Launches the Spring Boot application
	}

}
