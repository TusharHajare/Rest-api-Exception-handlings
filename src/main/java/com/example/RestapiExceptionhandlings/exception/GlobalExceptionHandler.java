package com.example.RestapiExceptionhandlings.exception;

import com.example.RestapiExceptionhandlings.models.ApiErrors;
//import com.example.RestapiExceptionhandlings.models.Book;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice  //means that spring knows that all the exceptions will be handled on this class
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    //need to use get but used post request
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
    (HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request method is not supported");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    //When I want to post the data using postman and object is created but type  selected is Text
    //And it requires Json
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported
    (HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("media is not supported");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);

    }

    //passing "author" and want @Pathvariable("author") that time this error will be created
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("path variable is missing");
        ApiErrors errors = new ApiErrors(message, details, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);

    }
      //books-by-category but passing parameter insted of using category use cat(missmatch type)
// Ex. @GetMapping("/books-by-category/{category}")
//    public ResponseEntity<List<Book>> getBookCategory(@RequestParam("cat") String category) {
   @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request param is missing");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    //Want to get the data by id but passes name to the url
    @Override
    protected ResponseEntity<Object> handleTypeMismatch
    (TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("mismatch of type");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

// When you want to post the data through postman but format(body) not created
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request body is not readable");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    //custom exceptions- This will point to the perticular class
    //When you entered book-author but author is not available in DB
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex)
    {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Book not found");
        ApiErrors errors = new ApiErrors(message, details,HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    //Entered id is not available in the database
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex)
    {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Id not found");
        ApiErrors errors = new ApiErrors(message, details,HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    //ExceptionHandler is not used for illegal,null pointer,data access,sql exceptions.

    //fallback handler -handle all the exceptions
    //when I entered 0 Zero then this error is occure
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleIdNotFoundException(Exception ex)
    {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Other exceptions");
        details.add(ex.getMessage());//exception msg is comming from run time exception
        ApiErrors errors = new ApiErrors(message, details,HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
