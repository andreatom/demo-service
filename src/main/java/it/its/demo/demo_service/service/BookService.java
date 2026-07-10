package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.BookDto;
import it.its.demo.demo_service.dto.BuyRequest;
import it.its.demo.demo_service.dto.InsertBook;
import it.its.demo.demo_service.dto.PatchBook;
import it.its.demo.demo_service.exceptions.BookNotFoundException;
import it.its.demo.demo_service.exceptions.BooksNotAvailable;
import it.its.demo.demo_service.mapper.BookMapper;
import it.its.demo.demo_service.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    @Autowired
    private BookMapper bookMapper;

    public BookDto insert(InsertBook insertBook) {
        Book book = bookMapper.toModel(insertBook);
        books.add(book);
        return bookMapper.toDto(book);
    }


    public BookDto findById(String id) {
        return books.stream()
                .filter(it -> it.getId().equals(id))
                .map(book -> bookMapper.toDto(book))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<BookDto> findAll() {
        return books.stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> findByName(String name) {
        return books.stream()
                .filter(it -> it.getName().equals(name))
                .map(book -> bookMapper.toDto(book))
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

        return bookMapper.toDto(book);
    }


    // PatchBook -> BookDto
    public BookDto patch(String id, PatchBook patchBook) {

        Book toUpdate = getBookById(id);

        if (patchBook.getAuthor() != null) {
            toUpdate.setAuthor(patchBook.getAuthor());
        }

        if (patchBook.getName() != null) {
            toUpdate.setName(patchBook.getName());
        }

        if (patchBook.getQuantity() != null) {
            toUpdate.setQuantity(patchBook.getQuantity());
        }

        return bookMapper.toDto(toUpdate);
    }

    // BookInsertDto -> BookDto
    public BookDto put(String id, InsertBook insert) {

        Book toUpdate = getBookById(id);
        toUpdate.setAuthor(insert.getAuthor());
        toUpdate.setName(insert.getName());
        toUpdate.setQuantity(insert.getQuantity());

        return bookMapper.toDto(toUpdate);
    }


    private Book getBookById(String id) {
        return books.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
