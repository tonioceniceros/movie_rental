package com.jaceniceros.movie_rental.repository;  // Defines the package location for this repository interface

import org.springframework.data.jpa.repository.JpaRepository;  // Imports JpaRepository to provide CRUD and query functionality
import com.jaceniceros.movie_rental.entity.Genre;  // Imports the Genre entity class that this repository manages

// The GenreRepository interface extends JpaRepository to enable database operations for the Genre entity.
// JpaRepository provides a complete set of ready-to-use CRUD methods such as save(), findById(), findAll(), and deleteById().
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // This repository manages Genre entities, each identified by a primary key of type Long.
    // No custom methods are defined here yet, but you can add custom query methods later, 
    // for example: List<Genre> findByName(String name);
}
