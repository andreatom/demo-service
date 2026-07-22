package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.author.ResAuthorDto;
import it.its.demo.demo_service.dto.book.*;
import it.its.demo.demo_service.dto.category.ResCategoryDto;
import it.its.demo.demo_service.dto.transaction.ReqBuyDto;
import it.its.demo.demo_service.dto.transaction.TransactionTotalDto;
import it.its.demo.demo_service.exceptions.BookDeletedException;
import it.its.demo.demo_service.exceptions.BookNotFoundException;
import it.its.demo.demo_service.exceptions.BooksNotAvailable;
import it.its.demo.demo_service.mapper.AuthorMapper;
import it.its.demo.demo_service.mapper.BookMapper;
import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.model.Transaction;
import it.its.demo.demo_service.repository.BookRepository;
import it.its.demo.demo_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryService categoryService;

    public ResBookDto insert(ReqInsertBook insertBook) {

        ResAuthorDto resAuthorDto = authorService.findById(insertBook.getAuthor());

        List<ResCategoryDto> categories = categoryService.findOrCreateByNames(insertBook.getCategories());

        Book book = bookMapper.toModel(insertBook, resAuthorDto, categories);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }


    public ResBookDto findById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDto(book);
    }

    public List<ResBookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    public List<ResBookDto> findByName(String name) {
        return bookRepository.findByNameWithQuery(name).stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    public void delete(String id) {

        bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );

        bookRepository.deleteById(id);

        throw new BookDeletedException(id);
    }

    public ResBookDto buy(String id, ReqBuyDto request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (book.getQuantity() <= request.getQuantity() - 1) {
            throw new BooksNotAvailable(id, request.getQuantity());
        }

        book.setQuantity(book.getQuantity() - request.getQuantity());

        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setTotal(request.getQuantity()*book.getPrice());

        transactionRepository.save(transaction);

        return bookMapper.toDto(book);
    }


//    // PatchBook -> BookDto
    public ResBookDto patch(String id, ReqPatchBookDto reqPatchBookDto) {

        Book toUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (reqPatchBookDto.getAuthor() != null) {
            toUpdate.setAuthor(
                    authorMapper.toModel(
                            authorService.findById(reqPatchBookDto.getAuthor())
                    )
            );
        }

        if (reqPatchBookDto.getName() != null) {
            toUpdate.setName(reqPatchBookDto.getName());
        }

        if (reqPatchBookDto.getQuantity() != null) {
            toUpdate.setQuantity(reqPatchBookDto.getQuantity());
        }

        if (reqPatchBookDto.getPrice() != null) {
            toUpdate.setPrice(reqPatchBookDto.getPrice());
        }

        return bookMapper.toDto(bookRepository.save(toUpdate));
    }

    public ResBookDto patch(ReqPatchBookDtoWithId patchBook) {

        Book toUpdate = bookRepository.findById(patchBook.getId())
                .orElseThrow(() -> new BookNotFoundException(patchBook.getId()));

        if (patchBook.getAuthor() != null) {
            toUpdate.setAuthor(
                    authorMapper.toModel(
                            authorService.findById(patchBook.getAuthor())
                    )
            );
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
    public ResBookDto put(String id, ReqInsertBook insert) {

        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        List<ResCategoryDto> categories = categoryService.findOrCreateByNames(insert.getCategories());

        ResAuthorDto resAuthorDto = authorService.findById(insert.getAuthor());
        Book bookToPut = bookMapper.toModel(insert, resAuthorDto, categories);
        bookToPut.setId(id);

        return bookMapper.toDto(bookRepository.save(bookToPut));

    }

    public ResBookDto put(ReqPutBookDto insert) {

        bookRepository.findById(insert.getId())
                .orElseThrow(() -> new BookNotFoundException(insert.getId()));

        List<ResCategoryDto> categories = categoryService.findOrCreateByNames(insert.getCategories());

        ResAuthorDto resAuthorDto = authorService.findById(insert.getAuthor());
        Book bookToPut = bookMapper.toModel(insert, resAuthorDto, categories);
        return bookMapper.toDto(bookRepository.save(bookToPut));

    }

    public TransactionTotalDto total(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        List<Transaction> transactions = transactionRepository.findByBook(book);
        Float total = transactions.stream().
                map(Transaction::getTotal)
                .reduce((float) 0, Float::sum);

        TransactionTotalDto transactionTotalDto = new TransactionTotalDto();
        transactionTotalDto.setBookId(id);
        transactionTotalDto.setTotal(total);
        return transactionTotalDto;
    }

}
