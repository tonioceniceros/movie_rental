package com.jaceniceros.movie_rental.repository;  // Defines the package location for this repository class

import org.springframework.data.jpa.repository.JpaRepository;  // Imports JpaRepository, which provides built-in CRUD operations
import com.jaceniceros.movie_rental.entity.Customer;  // Imports the Customer entity that this repository manages

// The CustomerRepository interface extends JpaRepository to handle database operations for the Customer entity.
// JpaRepository provides common methods such as save(), findById(), findAll(), deleteById(), and more.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // No additional methods are required here because JpaRepository already includes all standard CRUD functionality.
    // If needed in the future, you can define custom query methods (e.g., findByEmail(String email)).
}
