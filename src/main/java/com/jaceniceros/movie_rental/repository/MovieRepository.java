package com.jaceniceros.movie_rental.repository;  // Defines the package location for this repository interface

import org.springframework.data.jpa.repository.JpaRepository;  // Imports JpaRepository for built-in CRUD functionality
import com.jaceniceros.movie_rental.entity.Movie;  // Imports the Movie entity class that this repository will manage

// The MovieRepository interface extends JpaRepository, which provides access to standard CRUD methods.
// JpaRepository includes operations such as save(), findById(), findAll(), deleteById(), and count().
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // This repository manages Movie entities identified by a primary key of type Long.
    // No custom methods are required here yet, but additional query methods (e.g., findByTitle) can be added later if needed.
}
