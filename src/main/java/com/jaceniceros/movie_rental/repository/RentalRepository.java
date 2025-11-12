package com.jaceniceros.movie_rental.repository;  // Defines the package location for this repository interface

import org.springframework.data.jpa.repository.JpaRepository;  // Imports JpaRepository to provide CRUD operations
import com.jaceniceros.movie_rental.entity.Rental;  // Imports the Rental entity class that this repository will manage

// The RentalRepository interface extends JpaRepository, which gives access to built-in CRUD and query methods.
// JpaRepository provides operations such as save(), findById(), findAll(), deleteById(), and count().
public interface RentalRepository extends JpaRepository<Rental, Long> { 
    // This repository manages Rental entities, each identified by a primary key of type Long.
    // You can add custom queries later if needed, such as finding rentals by customer or date range.
    // Example: List<Rental> findByCustomerId(Long customerId);
}
