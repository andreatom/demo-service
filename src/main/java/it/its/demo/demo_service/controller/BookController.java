package it.its.demo.demo_service.controller;

import it.its.demo.demo_service.dto.*;
import it.its.demo.demo_service.repository.TransactionRepository;
import it.its.demo.demo_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto insert (
            @Valid @RequestBody InsertBook book
    ) {
        return bookService.insert(book);
    }

    @PatchMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public BookDto patch (
            @PathVariable String id,
            @RequestBody PatchBook patchBook
    ) {
        return bookService.patch(id, patchBook);
    }

    @PutMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public BookDto patch (
            @PathVariable String id,
            @RequestBody InsertBook insertBook
    ) {
        return bookService.put(id, insertBook);
    }

    @GetMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public BookDto findById(
            @PathVariable String id
    ){
        return bookService.findById(id);
    }

    @GetMapping("/search/{name}/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> findByName(
            @PathVariable String name
    ){
        return bookService.findByName(name);
    }

    @DeleteMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ){
       bookService.delete(id);
    }

    @PostMapping("/{id}/buy/v1")
    @ResponseStatus(HttpStatus.OK)
    public BookDto buy(
            @PathVariable String id,
            @Valid @RequestBody BuyRequest buyRequest
    ){
        return bookService.buy(id, buyRequest);
    }

    @GetMapping("/{id}/buy/total/v1")
    @ResponseStatus(HttpStatus.OK)
    public TransactionTotalDto transactionTotal(
            @PathVariable String id
    ){
        return bookService.total(id);
    }
}
