package com.jaceniceros.movie_rental.errorhandling;  // Defines the package location for the error-handling class

import java.time.LocalDateTime;  // Imports LocalDateTime for capturing when the error occurred
import java.util.List;  // Imports List to hold multiple error messages
import java.util.NoSuchElementException;  // Imports exception used when an entity is not found
import java.util.stream.Collectors;  // Imports Collectors for processing streams

import org.springframework.http.HttpStatus;  // Imports HTTP status codes
import org.springframework.http.ResponseEntity;  // Used to build and return HTTP responses
import org.springframework.web.bind.MethodArgumentNotValidException;  // Exception for validation errors on @Valid fields
import org.springframework.web.bind.annotation.ControllerAdvice;  // Enables centralized exception handling across controllers
import org.springframework.web.bind.annotation.ExceptionHandler;  // Marks a method as an exception handler
import org.springframework.web.bind.annotation.ResponseStatus;  // Allows specifying HTTP response codes for exceptions

import jakarta.validation.ConstraintViolationException;  // Handles validation errors from request parameters or paths
import lombok.Data;  // Lombok annotation to generate getters, setters, and other methods automatically

@ControllerAdvice  // Marks this class as a global error handler that applies to all controllers in the project
public class GlobalErrorHandler {  // Defines the class that manages application-wide exception handling

    // --- Handle 404 Not Found errors ---
    @ExceptionHandler(NoSuchElementException.class)  // Catches exceptions when a requested entity is not found
    @ResponseStatus(HttpStatus.NOT_FOUND)  // Returns HTTP 404 (Not Found) to the client
    public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException ex) {
        ErrorResponse error = new ErrorResponse(  // Creates a new error response object
                HttpStatus.NOT_FOUND.value(),  // Sets the HTTP status code (404)
                List.of(ex.getMessage()),  // Adds the specific error message from the exception
                LocalDateTime.now()  // Records the time the error occurred
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  // Returns a formatted error response with status 404
    }

    // --- Handle validation errors for invalid request bodies ---
    @ExceptionHandler(MethodArgumentNotValidException.class)  // Catches validation exceptions from @Valid annotations
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Returns HTTP 400 (Bad Request)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()  // Retrieves all field validation errors
                .getFieldErrors()  // Gets individual field-level errors
                .stream()  // Converts them into a stream
                .map(err -> err.getField() + ": " + err.getDefaultMessage())  // Maps each to a readable "field: message" format
                .collect(Collectors.toList());  // Collects all messages into a list

        ErrorResponse error = new ErrorResponse(  // Builds a custom error response object
                HttpStatus.BAD_REQUEST.value(),  // Sets HTTP status code 400
                errors,  // Adds the list of validation error messages
                LocalDateTime.now()  // Adds the timestamp
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);  // Returns the structured response with HTTP 400
    }

    // --- Handle constraint violations (e.g., invalid path or request parameter values) ---
    @ExceptionHandler(ConstraintViolationException.class)  // Catches validation errors outside @RequestBody (e.g., @PathVariable)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Returns HTTP 400 (Bad Request)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()  // Retrieves all violated constraints
                .stream()  // Converts them into a stream
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())  // Formats each violation message
                .collect(Collectors.toList());  // Collects all messages into a list

        ErrorResponse error = new ErrorResponse(  // Creates the custom error response
                HttpStatus.BAD_REQUEST.value(),  // Sets HTTP status 400
                errors,  // Includes all constraint violation messages
                LocalDateTime.now()  // Records the time of the error
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);  // Returns the error response with status 400
    }

    // --- Catch-all for any unexpected server errors ---
    @ExceptionHandler(Exception.class)  // Catches all other unhandled exceptions
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // Returns HTTP 500 (Internal Server Error)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse(  // Creates a response object for generic exceptions
                HttpStatus.INTERNAL_SERVER_ERROR.value(),  // Sets HTTP status code 500
                List.of(ex.getMessage()),  // Includes the exception message in the response
                LocalDateTime.now()  // Adds a timestamp for when the error occurred
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);  // Returns the formatted error response
    }

    // --- Inner class that defines the structure of the error response returned to clients ---
    @Data  // Lombok annotation that automatically generates getters, setters, and toString() methods
    static class ErrorResponse {
        private final int status;  // Stores the HTTP status code
        private final List<String> errors;  // Stores the list of detailed error messages
        private final LocalDateTime timestamp;  // Stores the time when the error occurred
    }
}
