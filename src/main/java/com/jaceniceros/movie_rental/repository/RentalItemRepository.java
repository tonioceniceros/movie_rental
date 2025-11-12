package com.jaceniceros.movie_rental.repository;  // Defines the package location for this repository interface

import org.springframework.data.jpa.repository.JpaRepository;  // Imports JpaRepository to provide built-in CRUD functionality
import com.jaceniceros.movie_rental.entity.RentalItem;  // Imports the RentalItem entity that this repository manages

// The RentalItemRepository interface extends JpaRepository, allowing it to handle database operations
// for RentalItem entities. JpaRepository automatically provides all standard CRUD operations.
// These include methods such as save(), findById(), findAll(), deleteById(), and count().
public interface RentalItemRepository extends JpaRepository<RentalItem, Long> { 
    // This repository manages RentalItem entities, which represent individual movie rentals within a Rental.
    // Each RentalItem is identified by a primary key of type Long.
    // Custom queries can be added later if needed — for example, to find rental items by rental ID or movie ID.
    // Example: List<RentalItem> findByRentalId(Long rentalId);
}
