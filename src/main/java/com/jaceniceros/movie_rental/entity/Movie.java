package com.jaceniceros.movie_rental.entity;  // Defines the package where this entity is located

import jakarta.persistence.*;  // Imports JPA annotations for ORM mapping
import jakarta.validation.constraints.*;  // Imports annotations for input validation
import lombok.Getter;  // Lombok annotation to auto-generate getter methods
import lombok.NoArgsConstructor;  // Lombok annotation to auto-generate a no-argument constructor
import lombok.Setter;  // Lombok annotation to auto-generate setter methods
import java.util.HashSet;  // Imports HashSet for storing unique genres
import java.util.Set;  // Imports Set interface for collections

@Entity  // Marks this class as a JPA entity that maps to a database table
@Getter  // Automatically creates getter methods for all fields
@Setter  // Automatically creates setter methods for all fields
@NoArgsConstructor  // Automatically generates a no-argument constructor
public class Movie {  // Defines the Movie entity class

    @Id  // Specifies that this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generates the ID (auto-increment in MySQL)
    private Long id;  // Unique identifier for each movie

    @NotBlank(message = "Title is required")  // Ensures the movie title cannot be empty
    private String title;  // Stores the movie's title

    @Min(value = 1880, message = "Release year must be valid")  // Ensures the year is realistic (after first movies)
    private Integer releaseYear;  // Stores the movie’s release year

    @NotBlank(message = "Rating is required")  // Ensures the rating (e.g., PG-13) cannot be blank
    private String rating;  // Stores the movie rating classification

    @NotNull(message = "Stock count is required")  // Ensures stock is provided
    @PositiveOrZero(message = "Stock cannot be negative")  // Ensures stock value is 0 or higher
    private Integer stock;  // Tracks how many copies are available for rental

    @ManyToMany  // Defines a many-to-many relationship between movies and genres
    @JoinTable(  // Specifies the join table that connects movies and genres
        name = "movie_genres",  // Name of the join table in the database
        joinColumns = @JoinColumn(name = "movie_id"),  // Column that references this movie
        inverseJoinColumns = @JoinColumn(name = "genre_id")  // Column that references the genre
    )
    private Set<Genre> genres = new HashSet<>();  // A set to store all genres linked to this movie
}
