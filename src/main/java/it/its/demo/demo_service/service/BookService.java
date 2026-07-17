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
import org.apache.catalina.mapper.Mapper;
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

    @Autowired
    private TransactionRepository transactionRepository;

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
//
//    public List<BookDto> findByName(String name) {
//        return bookRepository.findByName(name).stream()
//                .map(book -> bookMapper.toDto(book))
//                .collect(Collectors.toList());
//    }
//
    public void delete(String id) {

        bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );

        bookRepository.deleteById(id);

        throw new BookDeletedException(id);
    }
//
//    public BookDto buy(String id, BuyRequest request) {
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new BookNotFoundException(id));
//
//        if (book.getQuantity() <= request.getQuantity() - 1) {
//            throw new BooksNotAvailable(id, request.getQuantity());
//        }
//
//        book.setQuantity(book.getQuantity() - request.getQuantity());
//
//        Transaction transaction = new Transaction();
//        transaction.setBookId(id);
//        transaction.setTotal(request.getQuantity()*book.getPrice());
//
//        transactionRepository.saveTransaction(transaction);
//
//        int result = bookRepository.update(id, book);
//        if(result == 0){
//            throw new BookNotFoundException(id);
//        }
//
//        return bookMapper.toDto(book);
//    }
//
//
//    // PatchBook -> BookDto
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

        return bookMapper.toDto(bookRepository.save(toUpdate));
    }

    public BookDto patch(PatchBookWithId patchBook) {

        Book toUpdate = bookRepository.findById(patchBook.getId())
                .orElseThrow(() -> new BookNotFoundException(patchBook.getId()));

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

        return bookMapper.toDto(bookRepository.save(toUpdate));
    }
//
//    // BookInsertDto -> BookDto
    public BookDto put(String id, InsertBook insert) {

        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Book bookToPut = bookMapper.toModel(insert);
        bookToPut.setId(id);

        return bookMapper.toDto(bookRepository.save(bookToPut));

    }

    public BookDto put(PutBook insert) {

        bookRepository.findById(insert.getId())
                .orElseThrow(() -> new BookNotFoundException(insert.getId()));

        Book bookToPut = bookMapper.toModel(insert);
        return bookMapper.toDto(bookRepository.save(bookToPut));

    }
//
//    public TransactionTotalDto total(String id) {
//        List<Transaction> transactions = transactionRepository.findByBookId(id);
//        Float total = transactions.stream().
//                map(Transaction::getTotal)
//                .reduce((float) 0, Float::sum);
//
//        TransactionTotalDto transactionTotalDto = new TransactionTotalDto();
//        transactionTotalDto.setBookId(id);
//        transactionTotalDto.setTotal(total);
//        return transactionTotalDto;
//    }

}
