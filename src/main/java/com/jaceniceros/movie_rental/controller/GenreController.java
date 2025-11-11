package com.jaceniceros.movie_rental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jaceniceros.movie_rental.entity.Genre;
import com.jaceniceros.movie_rental.service.GenreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre create(@Valid @RequestBody Genre genre) {
        return genreService.create(genre);
    }

    @GetMapping
    public List<Genre> list() {
        return genreService.list();
    }

    @GetMapping("/{id}")
    public Genre get(@PathVariable Long id) {
        return genreService.get(id);
    }

    @PutMapping("/{id}")
    public Genre update(@PathVariable Long id, @Valid @RequestBody Genre genre) {
        return genreService.update(id, genre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }
}
