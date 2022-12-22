package com.example.digitallibraryhibernateboot.services;

import com.example.digitallibraryhibernateboot.models.Book;
import com.example.digitallibraryhibernateboot.models.Person;
import com.example.digitallibraryhibernateboot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> personById = peopleRepository.findById(id);
        return personById.orElseThrow();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Optional<Person> optionalPerson = Optional.of(peopleRepository.findById(id).orElseThrow());

        Person personToBeUpdated = optionalPerson.get();

        personToBeUpdated.setFullName(person.getFullName());
        personToBeUpdated.setYearOfBirth(person.getYearOfBirth());

        peopleRepository.save(personToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> assignedBooks(int id) {
        return peopleRepository.assignedBooks(id);
    }
}
