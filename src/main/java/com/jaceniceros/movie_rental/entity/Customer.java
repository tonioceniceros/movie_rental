package com.jaceniceros.movie_rental.entity;  // Defines the package location of this entity class

import jakarta.persistence.*;  // Imports JPA annotations for mapping this class to a database table
import jakarta.validation.constraints.Email;  // Imports annotation to validate that the email is properly formatted
import jakarta.validation.constraints.NotBlank;  // Imports annotation to ensure fields are not empty or null
import lombok.Getter;  // Lombok annotation to automatically generate getter methods
import lombok.NoArgsConstructor;  // Lombok annotation to generate a no-argument constructor
import lombok.Setter;  // Lombok annotation to automatically generate setter methods
import java.util.ArrayList;  // Imports ArrayList for creating lists
import java.util.List;  // Imports List interface for collections

@Entity  // Marks this class as a JPA entity (maps to a database table)
@Getter  // Automatically generates getter methods for all fields
@Setter  // Automatically generates setter methods for all fields
@NoArgsConstructor  // Creates a no-argument constructor (required by JPA)
public class Customer {  // Defines the Customer entity (represents a customer record in the database)

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates IDs (auto-increment in MySQL)
    private Long id;  // Unique identifier for each customer

    @NotBlank(message = "First name is required")  // Ensures the first name cannot be empty
    private String firstName;  // Stores the customer's first name

    @NotBlank(message = "Last name is required")  // Ensures the last name cannot be empty
    private String lastName;  // Stores the customer's last name

    @Email(message = "Email must be valid")  // Ensures the email follows a valid email format
    @NotBlank(message = "Email is required")  // Ensures the email field is not blank
    @Column(unique = true)  // Makes the email column unique in the database
    private String email;  // Stores the customer's email address

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)  
    // Defines a one-to-many relationship: one customer can have many rentals
    // 'mappedBy' links this relationship to the 'customer' field in the Rental entity
    // 'cascade = ALL' means all related rentals are updated/deleted automatically with the customer
    // 'orphanRemoval = true' deletes rentals if they’re no longer linked to a customer
    private List<Rental> rentals = new ArrayList<>();  // List to store all rentals associated with this customer
}
