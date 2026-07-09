package it.its.demo.demo_service.exceptions;

public class BooksNotAvailable extends RuntimeException {

    public BooksNotAvailable(String id, Integer quantity) {
        super("Not enough books. Id: " + id + ", Quantity: " + quantity);
    }
}
