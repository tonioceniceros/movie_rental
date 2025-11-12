package com.jaceniceros.movie_rental.controller;  // Defines the package location for this controller class

import java.util.List;  // Imports the List interface for handling multiple rental records

import org.springframework.http.HttpStatus;  // Imports HTTP status codes used for REST responses
import org.springframework.web.bind.annotation.*;  // Imports Spring MVC annotations for request mappings

import com.jaceniceros.movie_rental.entity.*;  // Imports the entity classes (Rental and RentalItem)
import com.jaceniceros.movie_rental.service.RentalService;  // Imports the service layer that handles rental-related logic

import lombok.RequiredArgsConstructor;  // Lombok annotation to automatically generate a constructor for final fields

@RestController  // Marks this class as a REST controller that returns JSON responses
@RequestMapping("/api/rentals")  // Sets the base URL for all endpoints in this controller
@RequiredArgsConstructor  // Generates a constructor for injecting required dependencies (RentalService)
public class RentalController {  // Defines the controller class that manages rental-related HTTP requests

    private final RentalService rentalService;  // Injects the service responsible for rental business logic

    // --- CRUD for rentals ---

    @PostMapping("/customer/{customerId}")  // Handles HTTP POST requests to create a rental for a specific customer
    @ResponseStatus(HttpStatus.CREATED)  // Returns HTTP 201 status when a rental is successfully created
    public Rental create(@PathVariable Long customerId, @RequestBody Rental rental) {  
        // Takes the customer ID from the URL and rental details from the request body
        return rentalService.create(customerId, rental);  // Calls the service layer to create and save the new rental
    }

    @GetMapping  // Handles HTTP GET requests to list all rentals
    public List<Rental> list() {  // Returns a list of all rentals
        return rentalService.list();  // Calls the service layer to retrieve all rental records
    }

    @GetMapping("/{id}")  // Handles HTTP GET requests to retrieve a rental by ID
    public Rental get(@PathVariable Long id) {  // Extracts the rental ID from the URL
        return rentalService.get(id);  // Calls the service layer to fetch the specific rental
    }

    @DeleteMapping("/{id}")  // Handles HTTP DELETE requests to remove a rental by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns HTTP 204 status (No Content) if deletion is successful
    public void delete(@PathVariable Long id) {  // Extracts the rental ID from the URL
        rentalService.delete(id);  // Calls the service layer to delete the rental record
    }

    // --- Manage movies in a rental ---

    @PostMapping("/{rentalId}/movies/{movieId}")  // Handles POST requests to add a movie to a rental
    @ResponseStatus(HttpStatus.CREATED)  // Returns HTTP 201 when a rental item is successfully added
    public RentalItem addMovie(
            @PathVariable Long rentalId,  // Extracts the rental ID from the URL
            @PathVariable Long movieId,  // Extracts the movie ID from the URL
            @RequestParam Integer days,  // Reads the rental duration (days) from the request parameter
            @RequestParam Double priceEach) {  // Reads the price per movie from the request parameter
        return rentalService.addMovie(rentalId, movieId, days, priceEach);  
        // Calls the service to add a movie as a rental item to the rental
    }

    @DeleteMapping("/items/{rentalItemId}")  // Handles DELETE requests to remove a specific movie from a rental
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns HTTP 204 (No Content) if the movie is successfully removed
    public void removeMovie(@PathVariable Long rentalItemId) {  // Extracts the rental item ID from the URL
        rentalService.removeMovie(rentalItemId);  // Calls the service layer to remove the movie from the rental
    }
}
