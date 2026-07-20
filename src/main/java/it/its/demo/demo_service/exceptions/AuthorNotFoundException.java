package it.its.demo.demo_service.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Integer id) {
        super("Autore non presente. ID: " + id);
    }
}
