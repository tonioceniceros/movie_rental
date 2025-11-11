package com.jaceniceros.movie_rental.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.jaceniceros.movie_rental.entity.*;
import com.jaceniceros.movie_rental.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final MovieRepository movieRepository;
    private final RentalItemRepository rentalItemRepository;

    // --- CRUD for Rentals ---
    public Rental create(Long customerId, Rental rental) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        rental.setCustomer(customer);
        return rentalRepository.save(rental);
    }

    public List<Rental> list() {
        return rentalRepository.findAll();
    }

    public Rental get(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rental not found"));
    }

    public void delete(Long id) {
        rentalRepository.deleteById(id);
    }

    // --- Manage RentalItems ---
    public RentalItem addMovie(Long rentalId, Long movieId, Integer days, Double priceEach) {
        Rental rental = get(rentalId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));

        RentalItem item = new RentalItem();
        item.setRental(rental);
        item.setMovie(movie);
        item.setDays(days);
        item.setPriceEach(priceEach);
        return rentalItemRepository.save(item);
    }

    public void removeMovie(Long rentalItemId) {
        rentalItemRepository.deleteById(rentalItemId);
    }
}
