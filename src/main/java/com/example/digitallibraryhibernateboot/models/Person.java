package com.example.digitallibraryhibernateboot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Book> assignedBooks;

}
