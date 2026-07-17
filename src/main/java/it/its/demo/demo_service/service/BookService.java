package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.*;
import it.its.demo.demo_service.exceptions.BookDeletedException;
import it.its.demo.demo_service.exceptions.BookNotFoundException;
import it.its.demo.demo_service.exceptions.BooksNotAvailable;
import it.its.demo.demo_service.mapper.BookMapper;
import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.model.Transaction;
import it.its.demo.demo_service.repository.BookRepository;
import it.its.demo.demo_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private final TransactionRepository transactionRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository, TransactionRepository transactionRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
    }

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
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findByName(String name) {
        return bookRepository.findByName(name).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(String id) {

        bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );

        bookRepository.deleteById(id);

        throw new BookDeletedException(id);
    }

    public BookDto buy(String id, BuyRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (book.getQuantity() < request.getQuantity()) {
            throw new BooksNotAvailable(id, request.getQuantity());
        }

        book.setQuantity(book.getQuantity() - request.getQuantity());

        Transaction transaction = new Transaction();
        transaction.setBookId(id);
        transaction.setTotal(request.getQuantity()*book.getPrice());

        transactionRepository.save(transaction);

        bookRepository.save(book);
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

        if (patchBook.getPrice() != null) {
            toUpdate.setPrice(patchBook.getPrice());
        }

        bookRepository.save(toUpdate);

        return bookMapper.toDto(toUpdate);
    }

    // BookInsertDto -> BookDto
    public BookDto put(String id, InsertBook insert) {

        Book toUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        toUpdate.setAuthor(insert.getAuthor());
        toUpdate.setName(insert.getName());
        toUpdate.setQuantity(insert.getQuantity());
        toUpdate.setPrice(insert.getPrice());

        bookRepository.save(toUpdate);

        return bookMapper.toDto(toUpdate);
    }

    public TransactionTotalDto total(String id) {
        Float total = transactionRepository.findAll().stream()
                .filter(t -> t.getBookId().equals(id))
                .map(Transaction::getTotal)
                .reduce((float) 0, Float::sum);

        TransactionTotalDto transactionTotalDto = new TransactionTotalDto();
        transactionTotalDto.setBookId(id);
        transactionTotalDto.setTotal(total);
        return transactionTotalDto;
    }

}
