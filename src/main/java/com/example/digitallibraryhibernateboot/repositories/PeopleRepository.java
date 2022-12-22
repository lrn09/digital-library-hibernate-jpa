package com.example.digitallibraryhibernateboot.repositories;

import com.example.digitallibraryhibernateboot.models.Book;
import com.example.digitallibraryhibernateboot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    @Query("select p.assignedBooks from Person p where p.personId = ?1")
    List<Book> assignedBooks(int id);

}
