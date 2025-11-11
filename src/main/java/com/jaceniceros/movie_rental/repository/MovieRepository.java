package com.jaceniceros.movie_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaceniceros.movie_rental.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
