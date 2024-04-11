package com.example.RestapiExceptionhandlings.service;

import com.example.RestapiExceptionhandlings.exception.BookNotFoundException;
import com.example.RestapiExceptionhandlings.exception.IdNotFoundException;
import com.example.RestapiExceptionhandlings.models.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{

    @Override
    public void addBook(Book book)
    {
        System.out.println(book);
    }

    @Override
    public List<Book> getBookByAuthor(String author)
    {
           List<Book> bookList = getBookList()
                .stream()
                .filter((book) ->book.getAuthor().equals(author))
                .collect(Collectors.toList());
            if (bookList.isEmpty())
            {
                throw new BookNotFoundException("Book is not available with entered author name");
            }
            return bookList;
    }

    @Override
    public List<Book> getBookByCategory(String category) {
        List<Book> bookList = getBookList()
                .stream()
                .filter((book) -> book.getCategory().equals(category))
                .collect(Collectors.toList());
        if (bookList.isEmpty()) {
            throw new BookNotFoundException("Book is not available with entered category name");
        }
        return bookList;
    }

    @Override
    public Book getBookById(int bookId)
    {
        if (bookId<=0)
        {
            throw new RuntimeException("other type of exception");//fallback handler
        }
//        Optional<Book> opt = getBookList()
        return getBookList()
                .stream()
                .filter((book) ->book.getBookid()==bookId)
                .findAny()
                .orElseThrow(() ->new IdNotFoundException("Invalid Id"));
//    if (opt.isPresent())
//    {
//       return opt.get();
//    }
//    else {
//        throw new IdNotFoundException("Invalid Id");
//    }

    }

    private List<Book> getBookList()
    {
        return Arrays.asList(new Book(10,"Tushar","Tech","Java"),
                new Book(11,"Rod","Tech","Spring Boot"),
                new Book(12,"Rob","Fiction","Miracal"),
                new Book(13,"John","Fighter","Captain"));
    }
}
