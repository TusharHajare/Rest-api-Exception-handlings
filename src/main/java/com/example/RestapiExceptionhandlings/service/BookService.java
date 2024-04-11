package com.example.RestapiExceptionhandlings.service;

import com.example.RestapiExceptionhandlings.exception.BookNotFoundException;
import com.example.RestapiExceptionhandlings.exception.IdNotFoundException;
import com.example.RestapiExceptionhandlings.models.Book;

import java.util.List;

public interface BookService
{
    void addBook(Book book);
    List<Book> getBookByAuthor(String author) throws BookNotFoundException;
    List<Book> getBookByCategory(String category) throws BookNotFoundException;
    Book getBookById(int bookId) throws IdNotFoundException;
}
