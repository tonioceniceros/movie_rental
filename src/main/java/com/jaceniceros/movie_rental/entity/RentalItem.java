package com.jaceniceros.movie_rental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"rental_id", "movie_id"}))
public class RentalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Rental rental;

    @ManyToOne(optional = false)
    private Movie movie;

    private Integer days;
    private Double priceEach;
}
