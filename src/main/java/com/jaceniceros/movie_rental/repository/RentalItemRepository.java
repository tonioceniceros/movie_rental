package com.jaceniceros.movie_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaceniceros.movie_rental.entity.RentalItem;

public interface RentalItemRepository extends JpaRepository<RentalItem, Long> { }
