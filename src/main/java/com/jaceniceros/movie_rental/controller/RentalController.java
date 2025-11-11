package com.jaceniceros.movie_rental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jaceniceros.movie_rental.entity.*;
import com.jaceniceros.movie_rental.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    // --- CRUD for rentals ---
    @PostMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Rental create(@PathVariable Long customerId, @RequestBody Rental rental) {
        return rentalService.create(customerId, rental);
    }

    @GetMapping
    public List<Rental> list() {
        return rentalService.list();
    }

    @GetMapping("/{id}")
    public Rental get(@PathVariable Long id) {
        return rentalService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rentalService.delete(id);
    }

    // --- Manage movies in a rental ---
    @PostMapping("/{rentalId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalItem addMovie(
            @PathVariable Long rentalId,
            @PathVariable Long movieId,
            @RequestParam Integer days,
            @RequestParam Double priceEach) {
        return rentalService.addMovie(rentalId, movieId, days, priceEach);
    }

    @DeleteMapping("/items/{rentalItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMovie(@PathVariable Long rentalItemId) {
        rentalService.removeMovie(rentalItemId);
    }
}
