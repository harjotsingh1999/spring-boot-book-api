package com.example.harjot.book.repositories;

import com.example.harjot.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from books b where b.author_id in (select id from authors where name like %:author% )", nativeQuery = true)
    List<Book> getBooksByAuthorName(@Param("author") String author);

    @Query(value = "select * from books b where b.genre_id in (select id from genre where name like %:genre% )", nativeQuery = true)
    List<Book> getBooksByGenre(@Param("genre") String genre);
}
