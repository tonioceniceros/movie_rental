package com.jaceniceros.movie_rental.controller;  // Defines the package location for the controller class

import java.util.List;  // Imports the List interface for handling collections of customers
import org.springframework.http.HttpStatus;  // Imports HTTP status codes for API responses
import org.springframework.web.bind.annotation.*;  // Imports Spring MVC annotations for REST controller mappings
import com.jaceniceros.movie_rental.entity.Customer;  // Imports the Customer entity
import com.jaceniceros.movie_rental.service.CustomerService;  // Imports the service layer that handles business logic
import jakarta.validation.Valid;  // Imports annotation for input validation
import lombok.RequiredArgsConstructor;  // Lombok annotation to auto-generate a constructor for final fields

@RestController  // Marks this class as a REST controller (returns data as JSON)
@RequestMapping("/api/customers")  // Base URL for all endpoints in this controller
@RequiredArgsConstructor  // Generates a constructor that injects the required CustomerService dependency
public class CustomerController {  // Defines the controller that manages customer-related API requests

    private final CustomerService customerService;  // Injects the service class that handles business logic

    @PostMapping  // Maps HTTP POST requests to this method for creating a new customer
    @ResponseStatus(HttpStatus.CREATED)  // Returns HTTP 201 status when a customer is successfully created
    public Customer create(@Valid @RequestBody Customer customer) {  // Takes a validated Customer object from the request body
        return customerService.create(customer);  // Calls the service layer to save the new customer
    }

    @GetMapping  // Maps HTTP GET requests to this method for listing all customers
    public List<Customer> list() {  // Returns a list of all customers
        return customerService.list();  // Calls the service layer to fetch customer records
    }

    @GetMapping("/{id}")  // Maps HTTP GET requests with a specific ID (e.g., /api/customers/1)
    public Customer get(@PathVariable Long id) {  // Extracts the customer ID from the URL
        return customerService.get(id);  // Calls the service layer to retrieve the customer by ID
    }

    @PutMapping("/{id}")  // Maps HTTP PUT requests for updating an existing customer by ID
    public Customer update(@PathVariable Long id, @Valid @RequestBody Customer customer) {  
        // Takes a validated Customer object and the ID of the customer to update
        return customerService.update(id, customer);  // Calls the service layer to update the record
    }

    @DeleteMapping("/{id}")  // Maps HTTP DELETE requests to delete a customer by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns HTTP 204 status (no content) when deletion is successful
    public void delete(@PathVariable Long id) {  // Extracts the customer ID from the URL
        customerService.delete(id);  // Calls the service layer to delete the customer record
    }
}
