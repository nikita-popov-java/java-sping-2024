package ru.nikitapopov.skillbox.mod5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikitapopov.skillbox.mod5.dto.BookDTO;
import ru.nikitapopov.skillbox.mod5.model.Book;
import ru.nikitapopov.skillbox.mod5.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<Book>> getBook(
            @RequestParam String title, @RequestParam String author) {
        return ResponseEntity.ok(bookService.findByTitleAndAuthor(title, author));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam String category) {
        return ResponseEntity.ok(bookService.findByCategoryName(category));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        book.setId(id);
        return ResponseEntity.ok(bookService.update(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}