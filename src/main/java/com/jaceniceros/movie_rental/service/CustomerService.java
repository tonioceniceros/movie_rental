package com.jaceniceros.movie_rental.service;  // Defines the package location for this service class

import java.util.List;  // Imports List to handle collections of Customer objects
import java.util.NoSuchElementException;  // Imports exception type for when a Customer is not found

import org.springframework.stereotype.Service;  // Marks this class as a Spring-managed service component

import com.jaceniceros.movie_rental.entity.Customer;  // Imports the Customer entity
import com.jaceniceros.movie_rental.repository.CustomerRepository;  // Imports the repository for Customer data access

import lombok.RequiredArgsConstructor;  // Lombok annotation to auto-generate constructor for dependency injection

@Service  // Marks this class as a service component managed by Spring
@RequiredArgsConstructor  // Generates a constructor to inject all final repository dependencies
public class CustomerService {  // Defines the CustomerService class that contains business logic for customers

    private final CustomerRepository customerRepository;  // Repository for performing CRUD operations on Customer entities

    // --- Create a new customer ---
    public Customer create(Customer customer) {
        return customerRepository.save(customer);  // Saves the provided Customer entity to the database and returns it
    }

    // --- Retrieve all customers ---
    public List<Customer> list() {
        return customerRepository.findAll();  // Retrieves and returns all Customer records from the database
    }

    // --- Retrieve a specific customer by ID ---
    public Customer get(Long id) {
        return customerRepository.findById(id)  // Searches for the Customer by its ID
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));  
                // Throws an exception if no Customer with the given ID exists
    }

    // --- Update an existing customer ---
    public Customer update(Long id, Customer customerDetails) {
        Customer customer = get(id);  // Retrieves the existing Customer or throws an exception if not found
        customer.setFirstName(customerDetails.getFirstName());  // Updates the Customer’s first name
        customer.setLastName(customerDetails.getLastName());  // Updates the Customer’s last name
        customer.setEmail(customerDetails.getEmail());  // Updates the Customer’s email address
        return customerRepository.save(customer);  // Saves the updated Customer and returns it
    }

    // --- Delete a customer by ID ---
    public void delete(Long id) {
        customerRepository.deleteById(id);  // Deletes the Customer from the database using its ID
    }
}
