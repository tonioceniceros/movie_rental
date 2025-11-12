package com.jaceniceros.movie_rental.service;  // Defines the package location for this service class

import java.util.List;  // Imports the List interface for returning collections of movies
import java.util.NoSuchElementException;  // Imports exception used when an entity is not found in the database

import org.springframework.stereotype.Service;  // Marks this class as a Spring-managed service component

import com.jaceniceros.movie_rental.entity.Genre;  // Imports the Genre entity
import com.jaceniceros.movie_rental.entity.Movie;  // Imports the Movie entity
import com.jaceniceros.movie_rental.repository.GenreRepository;  // Imports the repository for Genre data access
import com.jaceniceros.movie_rental.repository.MovieRepository;  // Imports the repository for Movie data access

import lombok.RequiredArgsConstructor;  // Lombok annotation to auto-generate constructor for dependency injection

@Service  // Marks this class as a service layer component managed by Spring
@RequiredArgsConstructor  // Generates a constructor that injects the required repository dependencies
public class MovieService {  // Defines the MovieService class that contains the business logic for movies

    private final MovieRepository movieRepository;  // Repository for performing CRUD operations on Movie entities
    private final GenreRepository genreRepository;  // Repository for managing Genre entities and relationships

    // --- Basic CRUD methods ---

    // Creates and saves a new movie in the database
    public Movie create(Movie movie) {
        return movieRepository.save(movie);  // Saves the provided movie entity and returns it
    }

    // Retrieves a list of all movies in the database
    public List<Movie> list() {
        return movieRepository.findAll();  // Calls the repository to return all movies
    }

    // Retrieves a specific movie by its ID
    public Movie get(Long id) {
        return movieRepository.findById(id)  // Searches for the movie in the database
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));  
                // Throws an exception if no movie exists with that ID
    }

    // Updates an existing movie’s information
    public Movie update(Long id, Movie details) {
        Movie movie = get(id);  // Retrieves the movie by its ID or throws an exception if not found
        movie.setTitle(details.getTitle());  // Updates the movie’s title
        movie.setReleaseYear(details.getReleaseYear());  // Updates the release year
        movie.setRating(details.getRating());  // Updates the movie’s rating (e.g., PG-13, R)
        movie.setStock(details.getStock());  // Updates the available stock count
        return movieRepository.save(movie);  // Saves the updated movie record and returns it
    }

    // Deletes a movie from the database using its ID
    public void delete(Long id) {
        movieRepository.deleteById(id);  // Calls the repository to remove the movie by ID
    }

    // --- Relationship management methods ---

    // Adds a genre to a movie (many-to-many relationship)
    public Movie addGenre(Long movieId, Long genreId) {
        Movie movie = get(movieId);  // Finds the movie by ID
        Genre genre = genreRepository.findById(genreId)  // Finds the genre by ID
                .orElseThrow(() -> new NoSuchElementException("Genre not found"));  
                // Throws an error if the genre doesn’t exist
        movie.getGenres().add(genre);  // Adds the genre to the movie’s genre list
        return movieRepository.save(movie);  // Saves the updated movie entity with the new genre
    }

    // Removes a genre from a movie
    public Movie removeGenre(Long movieId, Long genreId) {
        Movie movie = get(movieId);  // Finds the movie by ID
        movie.getGenres().removeIf(g -> g.getId().equals(genreId));  
        // Removes the genre that matches the given ID from the movie’s genre list
        return movieRepository.save(movie);  // Saves the updated movie after removing the genre
    }
}
