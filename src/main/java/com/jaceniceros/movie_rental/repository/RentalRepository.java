package com.jaceniceros.movie_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaceniceros.movie_rental.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> { }
