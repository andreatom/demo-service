package it.its.demo.demo_service.service;

import it.its.demo.demo_service.exceptions.BookNotFoundException;
import it.its.demo.demo_service.exceptions.BooksNotAvailable;
import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.model.BuyRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public Book insert(Book book) {
        books.add(book);
        return book;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(String id) {
        return books.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> findByName(String name) {
        return books.stream()
                .filter(it -> it.getName().equals(name))
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        findById(id);
        books.removeIf(it -> it.getId().equals(id));
    }

    public Book buy(String id, BuyRequest request) {
        Book book = findById(id);

        if (book.getQuantity() <= request.getQuantity()) {
            throw new BooksNotAvailable(id, request.getQuantity());
        }

        book.setQuantity(book.getQuantity() - request.getQuantity());

        return book;
    }

}
