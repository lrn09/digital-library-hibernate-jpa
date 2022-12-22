package com.example.digitallibraryhibernateboot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 30, message = "Title should be between 1 and 30 characters")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 3, max = 30, message = "Author should be between 2 and 30 characters")
    @Column(name = "author")
    private String author;
    @Min(value = 0, message = "Year of publication should be greater than 0")
    @Column(name = "year_of_publication")
    private Integer yearOfPublication;

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Integer ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "person_id")
    private Person owner;

}
