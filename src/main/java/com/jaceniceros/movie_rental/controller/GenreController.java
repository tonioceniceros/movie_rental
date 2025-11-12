package com.jaceniceros.movie_rental.controller;  // Defines the package location for this controller class

import java.util.List;  // Imports the List interface to handle multiple Genre objects
import org.springframework.http.HttpStatus;  // Imports HTTP status codes for API responses
import org.springframework.web.bind.annotation.*;  // Imports annotations for REST API mappings
import com.jaceniceros.movie_rental.entity.Genre;  // Imports the Genre entity
import com.jaceniceros.movie_rental.service.GenreService;  // Imports the service that handles genre-related logic
import jakarta.validation.Valid;  // Enables validation for incoming request data
import lombok.RequiredArgsConstructor;  // Lombok annotation to automatically generate a constructor for required fields

@RestController  // Marks this class as a REST controller that returns JSON data
@RequestMapping("/api/genres")  // Defines the base URL for all genre-related endpoints
@RequiredArgsConstructor  // Automatically generates a constructor to inject the GenreService dependency
public class GenreController {  // Defines the GenreController class

    private final GenreService genreService;  // Declares a final field for the service that handles business logic

    @PostMapping  // Handles HTTP POST requests to create a new genre
    @ResponseStatus(HttpStatus.CREATED)  // Returns HTTP 201 status code when a genre is successfully created
    public Genre create(@Valid @RequestBody Genre genre) {  
        // Takes a validated Genre object from the request body
        return genreService.create(genre);  // Calls the service layer to save the new genre
    }

    @GetMapping  // Handles HTTP GET requests to retrieve all genres
    public List<Genre> list() {  // Returns a list of genres
        return genreService.list();  // Calls the service layer to fetch all genres from the database
    }

    @GetMapping("/{id}")  // Handles GET requests to retrieve a specific genre by its ID
    public Genre get(@PathVariable Long id) {  // Extracts the genre ID from the URL path
        return genreService.get(id);  // Calls the service to find and return the genre
    }

    @PutMapping("/{id}")  // Handles HTTP PUT requests to update an existing genre by ID
    public Genre update(@PathVariable Long id, @Valid @RequestBody Genre genre) {  
        // Takes a validated Genre object from the request body and the ID from the URL
        return genreService.update(id, genre);  // Calls the service to update the genre record
    }

    @DeleteMapping("/{id}")  // Handles HTTP DELETE requests to remove a genre by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns HTTP 204 status (no content) when deletion succeeds
    public void delete(@PathVariable Long id) {  // Extracts the genre ID from the URL
        genreService.delete(id);  // Calls the service to delete the genre from the database
    }
}
