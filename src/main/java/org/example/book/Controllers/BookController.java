package org.example.book.Controllers;

import org.example.book.Models.Dto.BookCreateDTO;
import org.example.book.Models.Entity.Book;
import org.example.book.Services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO dto) {
        Book newBook = bookService.createBook(dto);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {

        return bookService.getBookById(id);
    }
}