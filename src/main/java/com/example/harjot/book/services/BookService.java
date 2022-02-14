package com.example.harjot.book.services;


import com.example.harjot.book.entities.Author;
import com.example.harjot.book.entities.Book;
import com.example.harjot.book.entities.Genre;
import com.example.harjot.book.exceptions.AuthorNotFoundException;
import com.example.harjot.book.exceptions.BookNotFoundException;
import com.example.harjot.book.exceptions.GenreNotFoundException;
import com.example.harjot.book.exceptions.InvalidInputException;
import com.example.harjot.book.repositories.AuthorRepository;
import com.example.harjot.book.repositories.BookRepository;
import com.example.harjot.book.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/api/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new BookNotFoundException("Book not found");
        return bookOptional.get();
    }


    @GetMapping("/api/books")
    public List<Book> getBookByAuthorName(@RequestParam(required = false) String author, @RequestParam(required = false) String genre) {
        System.out.println("getBookByAuthorName called author= " + author + " genre= " + genre);
        if (genre == null && author == null)
            return bookRepository.findAll();
        else if (author != null)
            return bookRepository.getBooksByAuthorName(author);
        else
            return bookRepository.getBooksByGenre(genre);
    }

    @PostMapping("/api/books")
    public Book createBook(@RequestBody Map<String, Object> body) {
        System.out.println("createBook called body= " + body);
        if (!(body.containsKey("title") && body.containsKey("author_id") && body.containsKey("genre_id")))
            throw new InvalidInputException("Book title, author Id, and genre Id must be present");
        Optional<Author> optionalAuthor = authorRepository.findById(Long.valueOf((Integer) body.get("author_id")));
        if (optionalAuthor.isEmpty())
            throw new AuthorNotFoundException("Author not found");
        Optional<Genre> optionalGenre = genreRepository.findById(Long.valueOf((Integer) body.get("genre_id")));
        if (optionalGenre.isEmpty())
            throw new GenreNotFoundException("Genre not found");

        Book newBook = new Book();
        newBook.setTitle((String) body.get("title"));
        newBook.setAuthor(optionalAuthor.get());
        newBook.setGenre(optionalGenre.get());
        return bookRepository.save(newBook);
    }
}
