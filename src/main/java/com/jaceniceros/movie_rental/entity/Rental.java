package com.jaceniceros.movie_rental.entity;  // Defines the package where this entity belongs

import jakarta.persistence.*;  // Imports JPA annotations for ORM mapping
import lombok.Getter;  // Lombok annotation to auto-generate getter methods
import lombok.NoArgsConstructor;  // Lombok annotation to auto-generate a no-argument constructor
import lombok.Setter;  // Lombok annotation to auto-generate setter methods
import java.time.LocalDate;  // Imports LocalDate for storing date values
import java.util.ArrayList;  // Imports ArrayList for creating a list
import java.util.List;  // Imports List interface for collections

@Entity  // Marks this class as a JPA entity (represents a database table)
@Getter  // Automatically creates getter methods for all fields
@Setter  // Automatically creates setter methods for all fields
@NoArgsConstructor  // Automatically creates a no-argument constructor (required by JPA)
public class Rental {  // Defines the Rental entity class

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates IDs (auto-increment in MySQL)
    private Long id;  // Unique identifier for each rental record

    private LocalDate rentalDate = LocalDate.now();  // Automatically sets the rental date to the current date
    private LocalDate dueDate;  // Stores the date when the rental is due
    private LocalDate returnDate;  // Stores the date when the rented item is returned

    private String status;  // Tracks rental status (e.g., OPEN, RETURNED, LATE)

    @ManyToOne  // Defines a many-to-one relationship with the Customer entity
    @JoinColumn(name = "customer_id")  // Creates a foreign key column linking to Customer
    private Customer customer;  // References the customer who made this rental

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    // Defines a one-to-many relationship with RentalItem
    // Each rental can have multiple rental items (movies rented)
    // Cascade ensures changes to rental also apply to rental items
    // OrphanRemoval deletes items that are no longer linked to a rental
    private List<RentalItem> rentalItems = new ArrayList<>();  // List of items rented in this rental
}
