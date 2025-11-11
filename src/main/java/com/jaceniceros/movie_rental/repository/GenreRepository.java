package com.jaceniceros.movie_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaceniceros.movie_rental.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
