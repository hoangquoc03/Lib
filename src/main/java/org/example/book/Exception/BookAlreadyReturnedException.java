package org.example.book.Exception;

public class BookAlreadyReturnedException
        extends RuntimeException {

    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}