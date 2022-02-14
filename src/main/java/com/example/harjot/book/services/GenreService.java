package com.example.harjot.book.services;

import com.example.harjot.book.entities.Author;
import com.example.harjot.book.entities.Book;
import com.example.harjot.book.entities.Genre;
import com.example.harjot.book.exceptions.GenreNotFoundException;
import com.example.harjot.book.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/api/genre")
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @GetMapping("/api/genre/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isEmpty())
            throw new GenreNotFoundException("Genre not found");
        return optionalGenre.get();
    }

    @GetMapping("api/genre/{id}/books")
    public List<Book> getGenreBooks(@PathVariable Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isEmpty())
            throw new GenreNotFoundException("Genre not found");
        return optionalGenre.get().getBooksList();
    }

    @PostMapping("/api/genre")
    public Genre createGenre(@RequestBody Genre genre) {
        System.out.println("createGenre called genre= " + genre);
        Genre savedGenre = genreRepository.save(genre);
        System.out.println("createGenre created genre= " + savedGenre);
        return savedGenre;
    }
}
