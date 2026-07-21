package it.its.demo.demo_service.controller;

import it.its.demo.demo_service.dto.book.*;
import it.its.demo.demo_service.dto.transaction.BuyRequest;
import it.its.demo.demo_service.dto.transaction.TransactionTotalDto;
import it.its.demo.demo_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public ResBookDto insert (@Valid @RequestBody ReqInsertBook book) {
        return bookService.insert(book);
    }

    @PatchMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public ResBookDto patch (
            @PathVariable String id,
            @RequestBody ReqPatchBookDto reqPatchBookDto
    ) {
        return bookService.patch(id, reqPatchBookDto);
    }

    @PatchMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public ResBookDto patch (
            @Valid @RequestBody ReqPatchBookDtoWithId patchBook
    ) {
        return bookService.patch(patchBook);
    }

    @PutMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public ResBookDto patch (
            @PathVariable String id,
            @RequestBody ReqInsertBook insertBook
    ) {
        return bookService.put(id, insertBook);
    }

    @PutMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public ResBookDto put (
            @Valid @RequestBody ReqPutBookDto putBook
    ) {
        return bookService.put(putBook);
    }

    @GetMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<ResBookDto> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public ResBookDto findById(
            @PathVariable String id
    ){
        return bookService.findById(id);
    }

    @GetMapping("/search/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<ResBookDto> findByName(
            @RequestParam String name
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
    public ResBookDto buy(
            @PathVariable String id,
            @Valid @RequestBody BuyRequest buyRequest
    ){
        return bookService.buy(id, buyRequest);
    }

    @GetMapping("/{id}/buy/total/v1")
    @ResponseStatus(HttpStatus.OK)
    public TransactionTotalDto transactionTotalDto(
            @PathVariable String id
    ){
        return bookService.total(id);
    }
}
