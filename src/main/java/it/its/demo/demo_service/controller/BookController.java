package it.its.demo.demo_service.controller;

import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.model.BuyRequest;
import it.its.demo.demo_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Book insert (
            @RequestBody Book book
    ) {
        return bookService.insert(book);
    }

    @PatchMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public Book patch (
            @PathVariable String id,
            @RequestBody Book book
    ) {
        return bookService.patch(id, book);
    }

    @GetMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public Book findById(
            @PathVariable String id
    ){
        return bookService.findById(id);
    }

    @GetMapping("/search/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findByName(
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
    public Book buy(
            @PathVariable String id,
            @RequestBody BuyRequest buyRequest
    ){
        return bookService.buy(id, buyRequest);
    }

}
