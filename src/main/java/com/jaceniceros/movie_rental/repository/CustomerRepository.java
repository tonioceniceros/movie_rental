package com.jaceniceros.movie_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaceniceros.movie_rental.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
