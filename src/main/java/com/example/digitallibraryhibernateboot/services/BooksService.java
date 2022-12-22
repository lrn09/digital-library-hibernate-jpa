package com.example.digitallibraryhibernateboot.services;

import com.example.digitallibraryhibernateboot.models.Book;
import com.example.digitallibraryhibernateboot.models.Person;
import com.example.digitallibraryhibernateboot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> findAll(String keyWord, Pageable pageable) {
        if (keyWord != null) {
            return booksRepository.findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyWord, pageable);
        }
        return booksRepository.findAll(pageable);

    }


    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }


    public Person findOwner(int id) {
        return findOne(id).getOwner();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {

        Book bookToBeUpdated = findOne(id);

        bookToBeUpdated.setTitle(book.getTitle());
        bookToBeUpdated.setAuthor(book.getAuthor());
        bookToBeUpdated.setYearOfPublication(book.getYearOfPublication());

        booksRepository.save(bookToBeUpdated);

    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        booksRepository.release(id);
    }

    @Transactional
    public void assign(int personId, int bookId) {
        booksRepository.assign(personId, bookId);
    }
}