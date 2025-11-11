package com.jaceniceros.movie_rental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaceniceros.movie_rental.controller.CustomerController;
import com.jaceniceros.movie_rental.repository.CustomerRepository;

@SpringBootTest
class MovieRentalApplicationTests {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void contextLoads() {
        // Verifies the Spring context loads correctly
        assertThat(customerController).isNotNull();
    }

    @Test
    void testCustomerRepositoryCRUD() {
        // Create
        var customer = new com.jaceniceros.movie_rental.entity.Customer();
        customer.setFirstName("Test");
        customer.setLastName("User");
        customer.setEmail("testuser@example.com");

        var saved = customerRepository.save(customer);
        assertThat(saved.getId()).isNotNull();

        // Read
        var found = customerRepository.findById(saved.getId());
        assertThat(found).isPresent();

        // Update
        saved.setLastName("Updated");
        var updated = customerRepository.save(saved);
        assertThat(updated.getLastName()).isEqualTo("Updated");

        // Delete
        customerRepository.delete(updated);
        var afterDelete = customerRepository.findById(saved.getId());
        assertThat(afterDelete).isEmpty();
    }
}
