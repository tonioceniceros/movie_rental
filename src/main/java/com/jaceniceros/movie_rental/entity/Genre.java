package com.jaceniceros.movie_rental.entity;  // Defines the package where this entity class belongs

import jakarta.persistence.*;  // Imports JPA annotations used for mapping this class to a database table
import jakarta.validation.constraints.NotBlank;  // Imports validation to ensure fields are not left blank
import lombok.Getter;  // Lombok annotation to automatically generate getter methods
import lombok.NoArgsConstructor;  // Lombok annotation to generate a no-argument constructor
import lombok.Setter;  // Lombok annotation to automatically generate setter methods

@Entity  // Marks this class as a JPA entity (represents a database table)
@Getter  // Automatically generates getter methods for all fields
@Setter  // Automatically generates setter methods for all fields
@NoArgsConstructor  // Generates a no-argument constructor (needed by JPA)
public class Genre {  // Defines the Genre entity class

    @Id  // Marks this field as the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID (auto-increment in MySQL)
    private Long id;  // Unique identifier for each genre

    @NotBlank(message = "Genre name cannot be empty")  // Ensures the genre name is not null or blank
    @Column(unique = true, nullable = false)  // Ensures the genre name is unique and required in the database
    private String name;  // Stores the name of the genre (e.g., Action, Comedy, Drama)
}
