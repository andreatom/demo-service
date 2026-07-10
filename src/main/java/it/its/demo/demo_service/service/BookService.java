package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.BookDto;
import it.its.demo.demo_service.dto.InsertBook;
import it.its.demo.demo_service.exceptions.BookNotFoundException;
import it.its.demo.demo_service.exceptions.BooksNotAvailable;
import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.dto.BuyRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookDto insert(InsertBook insertBook) {

        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(insertBook.getName());
        book.setAuthor(insertBook.getAuthor());
        book.setQuantity(insertBook.getQuantity());

        books.add(book);

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setQuantity(book.getQuantity());

        return bookDto;
    }


    public BookDto findById(String id) {
        return books.stream()
                .filter(it -> it.getId().equals(id))
                .map(book -> {
                    BookDto bookDto = new BookDto();
                    bookDto.setId(book.getId());
                    bookDto.setName(book.getName());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setQuantity(book.getQuantity());
                    return bookDto;
                })
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<BookDto> findAll() {
        return books.stream()
                .map(book -> {
                    BookDto bookDto = new BookDto();
                    bookDto.setId(book.getId());
                    bookDto.setName(book.getName());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setQuantity(book.getQuantity());
                    return bookDto;
                }).collect(Collectors.toList());
    }

    public List<BookDto> findByName(String name) {
        return books.stream()
                .filter(it -> it.getName().equals(name))
                .map(book -> {
                    BookDto bookDto = new BookDto();
                    bookDto.setId(book.getId());
                    bookDto.setName(book.getName());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setQuantity(book.getQuantity());
                    return bookDto;
                })
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        findById(id);
        books.removeIf(it -> it.getId().equals(id));
    }

    public BookDto buy(String id, BuyRequest request) {
        Book book = getBookById(id);

        if (book.getQuantity() <= request.getQuantity() - 1) {
            throw new BooksNotAvailable(id, request.getQuantity());
        }

        book.setQuantity(book.getQuantity() - request.getQuantity());

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setQuantity(book.getQuantity());

        return bookDto;
    }


    // PatchBook -> BookDto
    public Book patch(String id, Book insert) {

        Book toUpdate = getBookById(id);

        if (insert.getAuthor() != null) {
            toUpdate.setAuthor(insert.getAuthor());
        }

        if (insert.getName() != null) {
            toUpdate.setName(insert.getName());
        }

        if (insert.getQuantity() != null) {
            toUpdate.setQuantity(insert.getQuantity());
        }

        BookDto bookDto = new BookDto();
        bookDto.setId(toUpdate.getId());
        bookDto.setName(toUpdate.getName());
        bookDto.setAuthor(toUpdate.getAuthor());
        bookDto.setQuantity(toUpdate.getQuantity());

        return toUpdate;
    }

    // BookInsertDto -> BookDto
    public BookDto put(String id, Book insert) {

        Book toUpdate = getBookById(id);
        toUpdate.setAuthor(insert.getAuthor());
        toUpdate.setName(insert.getName());
        toUpdate.setQuantity(insert.getQuantity());


        BookDto bookDto = new BookDto();
        bookDto.setId(toUpdate.getId());
        bookDto.setName(toUpdate.getName());
        bookDto.setAuthor(toUpdate.getAuthor());
        bookDto.setQuantity(toUpdate.getQuantity());

        return bookDto;
    }


    private Book getBookById(String id) {
        return books.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
