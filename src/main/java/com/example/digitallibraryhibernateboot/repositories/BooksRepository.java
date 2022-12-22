package com.example.digitallibraryhibernateboot.repositories;


import com.example.digitallibraryhibernateboot.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    Page<Book> findAll(Pageable pageable);

    @Modifying
    @Query("update Book b set b.ownerId = null where b.bookId = ?1")
    void release(int id);

    @Modifying
    @Query("update Book b set b.ownerId = ?1 where b.bookId = ?2")
    void assign(int personId, int bookId);

    @Query("select b from Book b " +
            "where upper(b.title) like upper(concat('%', ?1, '%')) or upper(b.author) like upper(concat('%', ?1, '%'))")
    Page<Book> findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String keyWord, Pageable pageable);

}
