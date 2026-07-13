package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.BookDto;
import it.its.demo.demo_service.dto.BuyRequest;
import it.its.demo.demo_service.dto.InsertBook;
import it.its.demo.demo_service.dto.PatchBook;
import it.its.demo.demo_service.exceptions.BookNotFoundException;
import it.its.demo.demo_service.exceptions.BooksNotAvailable;
import it.its.demo.demo_service.mapper.BookMapper;
import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    public BookDto insert(InsertBook insertBook) {
        Book book = bookMapper.toModel(insertBook);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }


    public BookDto findById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDto(book);
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> findByName(String name) {
        return bookRepository.findByName(name).stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        int result = bookRepository.delete(id);
        if(result == 0){
            throw new BookNotFoundException(id);
        }
    }

    public BookDto buy(String id, BuyRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (book.getQuantity() <= request.getQuantity() - 1) {
            throw new BooksNotAvailable(id, request.getQuantity());
        }

        book.setQuantity(book.getQuantity() - request.getQuantity());

        int result = bookRepository.update(id, book);
        if(result == 0){
            throw new BookNotFoundException(id);
        }

        return bookMapper.toDto(book);
    }


    // PatchBook -> BookDto
    public BookDto patch(String id, PatchBook patchBook) {

        Book toUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (patchBook.getAuthor() != null) {
            toUpdate.setAuthor(patchBook.getAuthor());
        }

        if (patchBook.getName() != null) {
            toUpdate.setName(patchBook.getName());
        }

        if (patchBook.getQuantity() != null) {
            toUpdate.setQuantity(patchBook.getQuantity());
        }

        int result = bookRepository.update(id, toUpdate);
        if(result == 0){
            throw new BookNotFoundException(id);
        }

        return bookMapper.toDto(toUpdate);
    }

    // BookInsertDto -> BookDto
    public BookDto put(String id, InsertBook insert) {

        Book toUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        toUpdate.setAuthor(insert.getAuthor());
        toUpdate.setName(insert.getName());
        toUpdate.setQuantity(insert.getQuantity());

        int result = bookRepository.update(id, toUpdate);
        if(result == 0){
            throw new BookNotFoundException(id);
        }

        return bookMapper.toDto(toUpdate);
    }

}
