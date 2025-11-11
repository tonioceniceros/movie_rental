package com.jaceniceros.movie_rental.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.jaceniceros.movie_rental.entity.Genre;
import com.jaceniceros.movie_rental.entity.Movie;
import com.jaceniceros.movie_rental.repository.GenreRepository;
import com.jaceniceros.movie_rental.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    // --- Basic CRUD ---
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> list() {
        return movieRepository.findAll();
    }

    public Movie get(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }

    public Movie update(Long id, Movie details) {
        Movie movie = get(id);
        movie.setTitle(details.getTitle());
        movie.setReleaseYear(details.getReleaseYear());
        movie.setRating(details.getRating());
        movie.setStock(details.getStock());
        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    // --- Relationship methods ---
    public Movie addGenre(Long movieId, Long genreId) {
        Movie movie = get(movieId);
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NoSuchElementException("Genre not found"));
        movie.getGenres().add(genre);
        return movieRepository.save(movie);
    }

    public Movie removeGenre(Long movieId, Long genreId) {
        Movie movie = get(movieId);
        movie.getGenres().removeIf(g -> g.getId().equals(genreId));
        return movieRepository.save(movie);
    }
}
