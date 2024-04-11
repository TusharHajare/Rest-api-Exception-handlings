package com.example.RestapiExceptionhandlings.exception;


//we are creating the custom / RUNTIME / unchecked exceptions
public class BookNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public BookNotFoundException()
    {
        super();
    }

    public BookNotFoundException(String args)
    {
        super(args);
    }
}
