package com.example.harjot.book.repositories;

import com.example.harjot.book.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface GenreRepository extends JpaRepository<Genre, Long> {
}
