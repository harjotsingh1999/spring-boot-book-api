package com.example.harjot.book.entities;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Author name cannot be blank")
    @NotEmpty(message = "Author name cannot be empty")
    @Length(message = "Author name cannot be less than 2 characters", min = 2)
    @NotNull(message = "Author name cannot be null")
    private String name;

    @OneToMany(mappedBy = "author", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("author")
    @JsonProperty("books")
    private List<Book> booksList= new ArrayList<>();

    public Author(Long id, String name, List<Book> booksList) {
        this.id = id;
        this.name = name;
        this.booksList = booksList;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
