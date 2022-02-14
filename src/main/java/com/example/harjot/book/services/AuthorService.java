package com.example.harjot.book.services;

import com.example.harjot.book.entities.Author;
import com.example.harjot.book.entities.Book;
import com.example.harjot.book.exceptions.AuthorNotFoundException;
import com.example.harjot.book.repositories.AuthorRepository;
import com.example.harjot.book.repositories.BookRepository;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/api/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/api/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        System.out.println("getAuthorById called id= " + id);
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())
            throw new AuthorNotFoundException("Author not found");
        Author author = optionalAuthor.get();
        System.out.println("author=" + author + " books= " + author.getBooksList());
        return author;
    }

    @GetMapping("api/authors/{id}/books")
    public List<Book> getAuthorBooks(@PathVariable Long id){
        System.out.println("getAuthorBooks called id= " + id);
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())
            throw new AuthorNotFoundException("Author not found");
        return optionalAuthor.get().getBooksList();
    }

    @PostMapping("/api/authors")
    public Author createAuthor(@RequestBody Author author)
    {
        System.out.println("createAuthor called author= "+author);
        Author savedAuthor = authorRepository.save(author);
        System.out.println("createAuthor created author= "+savedAuthor);
        return savedAuthor;
    }
}
