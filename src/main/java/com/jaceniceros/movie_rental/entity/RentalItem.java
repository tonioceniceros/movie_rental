package com.jaceniceros.movie_rental.entity;  // Defines the package where this entity belongs

import jakarta.persistence.*;  // Imports JPA annotations used for database mapping
import lombok.Getter;  // Lombok annotation that auto-generates getter methods
import lombok.NoArgsConstructor;  // Lombok annotation that auto-generates a no-argument constructor
import lombok.Setter;  // Lombok annotation that auto-generates setter methods

@Entity  // Marks this class as a JPA entity (maps to a table in the database)
@Getter  // Automatically generates getter methods for all fields
@Setter  // Automatically generates setter methods for all fields
@NoArgsConstructor  // Automatically generates a no-argument constructor required by JPA
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"rental_id", "movie_id"}))
 // Ensures each combination of rental and movie is unique (prevents duplicate entries for the same movie in one rental)
public class RentalItem {  // Defines the RentalItem entity class

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates IDs (auto-increment in MySQL)
    private Long id;  // Unique identifier for each rental item record

    @ManyToOne(optional = false)  // Defines a many-to-one relationship to the Rental entity (required field)
    private Rental rental;  // References the rental that this item belongs to

    @ManyToOne(optional = false)  // Defines a many-to-one relationship to the Movie entity (required field)
    private Movie movie;  // References the movie being rented in this rental

    private Integer days;  // Number of days the movie is rented for
    private Double priceEach;  // Price charged for renting this movie
}
