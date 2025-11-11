package com.jaceniceros.movie_rental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jaceniceros.movie_rental.entity.Movie;
import com.jaceniceros.movie_rental.service.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@Valid @RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @GetMapping
    public List<Movie> list() {
        return movieService.list();
    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        return movieService.get(id);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @Valid @RequestBody Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

    // --- Manage genres for a movie ---
    @PostMapping("/{movieId}/genres/{genreId}")
    public Movie addGenre(@PathVariable Long movieId, @PathVariable Long genreId) {
        return movieService.addGenre(movieId, genreId);
    }

    @DeleteMapping("/{movieId}/genres/{genreId}")
    public Movie removeGenre(@PathVariable Long movieId, @PathVariable Long genreId) {
        return movieService.removeGenre(movieId, genreId);
    }
}
