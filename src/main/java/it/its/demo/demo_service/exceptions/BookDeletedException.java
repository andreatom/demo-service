package it.its.demo.demo_service.exceptions;

public class BookDeletedException extends RuntimeException {
    public BookDeletedException(String id) {
        super("Book with ID " + id + " has been deleted.");
    }
}
