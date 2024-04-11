package com.example.RestapiExceptionhandlings.controller;

import com.example.RestapiExceptionhandlings.models.Book;
import com.example.RestapiExceptionhandlings.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book-restapi")
public class BookController
{
    @Autowired
    BookService bookService;

    @GetMapping("/getall")
    public ResponseEntity<String> hello() {
        String msg = "Welcome to book app";
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "Online Book Appliation");
        return new ResponseEntity<String>(msg, header, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody Book book)
    {
        bookService.addBook(book);
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "Adding one book");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookId(@PathVariable("id") int bookid) {
        Book book = bookService.getBookById(bookid);
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "Getting book by id");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(book);
    }

    @GetMapping("/books-by-author/{author}")
    public ResponseEntity<List<Book>> getBookAuthor(@PathVariable("author") String author) {
        List<Book> bookList = bookService.getBookByAuthor(author);
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/books-by-category/{category}")
    public ResponseEntity<List<Book>> getBookCategory(@RequestParam("category") String category) {
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "List of books by category");
        header.add("type", "book object");
        List<Book> bookList = bookService.getBookByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(bookList);
    }
}
