package com.example.harjot.book.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Genre cannot be null")
    @NotBlank(message = "Genre cannot be blank")
    @NotEmpty(message = "Genre cannot be empty")
    String name;

    @OneToMany(mappedBy = "genre", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("genre")
    @JsonProperty("books")
    List<Book> booksList= new ArrayList<>();

    public Genre() {
    }

    public Genre(Long id, String name, List<Book> booksList) {
        this.id = id;
        this.name = name;
        this.booksList = booksList;
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
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
