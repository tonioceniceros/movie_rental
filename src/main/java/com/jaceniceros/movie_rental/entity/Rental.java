package com.jaceniceros.movie_rental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Customer customer;

    private LocalDate rentalDate = LocalDate.now();
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status; // "OPEN" or "CLOSED"

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalItem> rentalItems = new ArrayList<>();
}
