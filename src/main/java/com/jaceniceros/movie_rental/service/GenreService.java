package com.jaceniceros.movie_rental.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.jaceniceros.movie_rental.entity.Genre;
import com.jaceniceros.movie_rental.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> list() {
        return genreRepository.findAll();
    }

    public Genre get(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Genre not found"));
    }

    public Genre update(Long id, Genre genreDetails) {
        Genre genre = get(id);
        genre.setName(genreDetails.getName());
        return genreRepository.save(genre);
    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
