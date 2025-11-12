package com.jaceniceros.movie_rental.service;  // Defines the package location for this service class

import java.util.List;  // Imports List to handle collections of Genre objects
import java.util.NoSuchElementException;  // Imports exception type for when a Genre is not found

import org.springframework.stereotype.Service;  // Marks this class as a Spring-managed service component

import com.jaceniceros.movie_rental.entity.Genre;  // Imports the Genre entity class
import com.jaceniceros.movie_rental.repository.GenreRepository;  // Imports the repository for Genre database access

import lombok.RequiredArgsConstructor;  // Lombok annotation that generates a constructor for final fields

@Service  // Marks this class as a Spring Service, part of the business logic layer
@RequiredArgsConstructor  // Generates a constructor for dependency injection of the GenreRepository
public class GenreService {  // Defines the GenreService class that manages Genre business logic

    private final GenreRepository genreRepository;  // Repository used to access and manipulate Genre records in the database

    // --- Create a new genre ---
    public Genre create(Genre genre) {
        return genreRepository.save(genre);  // Saves the provided genre object to the database and returns it
    }

    // --- Retrieve all genres ---
    public List<Genre> list() {
        return genreRepository.findAll();  // Fetches and returns a list of all Genre records from the database
    }

    // --- Retrieve a specific genre by ID ---
    public Genre get(Long id) {
        return genreRepository.findById(id)  // Looks up a Genre record by its ID
                .orElseThrow(() -> new NoSuchElementException("Genre not found"));  
                // Throws an error if the genre with the given ID does not exist
    }

    // --- Update an existing genre ---
    public Genre update(Long id, Genre genreDetails) {
        Genre genre = get(id);  // Retrieves the genre to be updated (throws error if not found)
        genre.setName(genreDetails.getName());  // Updates the name field with the new value
        return genreRepository.save(genre);  // Saves and returns the updated genre record
    }

    // --- Delete a genre by ID ---
    public void delete(Long id) {
        genreRepository.deleteById(id);  // Removes the genre from the database using its ID
    }
}
