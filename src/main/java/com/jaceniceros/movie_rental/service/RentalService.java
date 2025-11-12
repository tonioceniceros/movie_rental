package com.jaceniceros.movie_rental.service;  // Defines the package location for this service class

import java.util.List;  // Imports the List interface for returning collections of data
import java.util.NoSuchElementException;  // Imports exception type for when an entity is not found

import org.springframework.stereotype.Service;  // Marks this class as a Spring service component

import com.jaceniceros.movie_rental.entity.*;  // Imports the entity classes (Rental, Movie, Customer, RentalItem)
import com.jaceniceros.movie_rental.repository.*;  // Imports the repository interfaces for data access

import lombok.RequiredArgsConstructor;  // Lombok annotation that automatically generates a constructor for final fields

@Service  // Marks this class as a Spring-managed service, part of the business logic layer
@RequiredArgsConstructor  // Generates a constructor to inject all final repository dependencies
public class RentalService {  // Defines the RentalService class which handles business logic for rentals

    // --- Repository dependencies for data access ---
    private final RentalRepository rentalRepository;  // Repository for performing CRUD operations on rentals
    private final CustomerRepository customerRepository;  // Repository for accessing customer data
    private final MovieRepository movieRepository;  // Repository for retrieving movie data
    private final RentalItemRepository rentalItemRepository;  // Repository for managing rental item records

    // --- CRUD Operations for Rentals ---

    // Creates a new rental for a specific customer
    public Rental create(Long customerId, Rental rental) {
        Customer customer = customerRepository.findById(customerId)  // Looks up the customer by ID
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));  
                // Throws an error if the customer ID doesn’t exist in the database
        rental.setCustomer(customer);  // Links the rental to the customer
        return rentalRepository.save(rental);  // Saves the rental in the database and returns the created record
    }

    // Retrieves a list of all rentals in the system
    public List<Rental> list() {
        return rentalRepository.findAll();  // Calls the repository to fetch and return all rental records
    }

    // Retrieves a specific rental by its ID
    public Rental get(Long id) {
        return rentalRepository.findById(id)  // Searches for the rental by its ID
                .orElseThrow(() -> new NoSuchElementException("Rental not found"));  
                // Throws an error if the rental does not exist
    }

    // Deletes a rental by its ID
    public void delete(Long id) {
        rentalRepository.deleteById(id);  // Removes the rental from the database using its ID
    }

    // --- Manage RentalItems ---

    // Adds a movie to an existing rental (creates a RentalItem)
    public RentalItem addMovie(Long rentalId, Long movieId, Integer days, Double priceEach) {
        Rental rental = get(rentalId);  // Retrieves the rental using its ID
        Movie movie = movieRepository.findById(movieId)  // Looks up the movie by its ID
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));  
                // Throws an error if the movie does not exist

        RentalItem item = new RentalItem();  // Creates a new RentalItem instance
        item.setRental(rental);  // Associates the rental item with the rental
        item.setMovie(movie);  // Associates the rental item with the movie
        item.setDays(days);  // Sets the number of days the movie will be rented
        item.setPriceEach(priceEach);  // Sets the price per movie rented
        return rentalItemRepository.save(item);  // Saves the new rental item record and returns it
    }

    // Removes a movie (RentalItem) from a rental
    public void removeMovie(Long rentalItemId) {
        rentalItemRepository.deleteById(rentalItemId);  // Deletes the rental item by its ID
    }
}
