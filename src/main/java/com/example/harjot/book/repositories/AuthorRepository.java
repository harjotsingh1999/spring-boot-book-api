package com.example.harjot.book.repositories;

import com.example.harjot.book.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface AuthorRepository extends JpaRepository<Author, Long> {
}
