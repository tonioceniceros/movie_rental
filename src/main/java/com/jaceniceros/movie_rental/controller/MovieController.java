package com.jaceniceros.movie_rental.controller;  // Defines the package location of this controller class

import java.util.List;  // Imports the List interface for handling multiple movie objects
import org.springframework.http.HttpStatus;  // Imports HTTP status codes for API responses
import org.springframework.web.bind.annotation.*;  // Imports annotations for REST endpoints
import com.jaceniceros.movie_rental.entity.Movie;  // Imports the Movie entity
import com.jaceniceros.movie_rental.service.MovieService;  // Imports the service layer that handles movie logic
import jakarta.validation.Valid;  // Enables validation on request bodies
import lombok.RequiredArgsConstructor;  // Lombok annotation to generate constructor-based dependency injection

@RestController  // Marks this class as a REST controller that returns JSON responses
@RequestMapping("/api/movies")  // Defines the base URL for all movie-related API endpoints
@RequiredArgsConstructor  // Auto-generates a constructor for final fields (injects MovieService)
public class MovieController {  // Defines the MovieController class to handle HTTP requests for movies

    private final MovieService movieService;  // Injects the MovieService to handle business logic

    @PostMapping  // Handles HTTP POST requests to create a new movie
    @ResponseStatus(HttpStatus.CREATED)  // Returns HTTP 201 (Created) when successful
    public Movie create(@Valid @RequestBody Movie movie) {  // Takes a validated Movie object from the request body
        return movieService.create(movie);  // Calls the service layer to save the new movie to the database
    }

    @GetMapping  // Handles HTTP GET requests to retrieve all movies
    public List<Movie> list() {  // Returns a list of movies
        return movieService.list();  // Calls the service layer to fetch all movie records
    }

    @GetMapping("/{id}")  // Handles GET requests to retrieve a specific movie by ID
    public Movie get(@PathVariable Long id) {  // Extracts the movie ID from the URL
        return movieService.get(id);  // Calls the service layer to find and return the movie
    }

    @PutMapping("/{id}")  // Handles HTTP PUT requests to update an existing movie
    public Movie update(@PathVariable Long id, @Valid @RequestBody Movie movie) {  
        // Takes the movie ID and updated movie data from the request body
        return movieService.update(id, movie);  // Calls the service layer to update the movie record
    }

    @DeleteMapping("/{id}")  // Handles HTTP DELETE requests to remove a movie by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns HTTP 204 (No Content) if the movie is successfully deleted
    public void delete(@PathVariable Long id) {  // Extracts the movie ID from the URL
        movieService.delete(id);  // Calls the service layer to delete the movie
    }

    @PostMapping("/{movieId}/genres/{genreId}")  // Handles POST requests to link a genre to a movie
    public Movie addGenre(@PathVariable Long movieId, @PathVariable Long genreId) {  
        // Extracts both movie ID and genre ID from the URL path
        return movieService.addGenre(movieId, genreId);  // Calls the service to associate the genre with the movie
    }

    @DeleteMapping("/{movieId}/genres/{genreId}")  // Handles DELETE requests to unlink a genre from a movie
    public Movie removeGenre(@PathVariable Long movieId, @PathVariable Long genreId) {  
        // Extracts both IDs from the URL path
        return movieService.removeGenre(movieId, genreId);  // Calls the service to remove the genre association
    }
}
