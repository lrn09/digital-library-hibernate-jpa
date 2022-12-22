package com.example.digitallibraryhibernateboot.controllers;


import com.example.digitallibraryhibernateboot.models.Book;
import com.example.digitallibraryhibernateboot.services.BooksService;
import com.example.digitallibraryhibernateboot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "keyword", required = false) String keyWord,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "2") Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> bookPage = booksService.findAll(keyWord, pageable);
        List<Book> bookList = bookPage.getContent();

        model.addAttribute("bookPage", bookPage);
        model.addAttribute("bookList", bookList);

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("person", booksService.findOwner(id));
        return "books/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}")
    public String assign(@RequestParam("personId") int personId, @PathVariable("id") int bookId) {
        booksService.assign(personId, bookId);
        return "redirect:/books/{id}";
    }
}