package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.*;
import it.its.demo.demo_service.model.Book;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookMapper {

    public BookDto toDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    public Book toModel(InsertBook bookDto){
        Book book = new Book();
        //book.setId(UUID.randomUUID().toString());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }

    public Book toModel(String id, PatchBook patchBook){
        Book book = new Book();
        book.setId(id);
        book.setName(patchBook.getName());
        book.setAuthor(patchBook.getAuthor());
        book.setQuantity(patchBook.getQuantity());
        book.setPrice(patchBook.getPrice());
        return book;
    }

    public Book toModel(PatchBookWithId patchBook){
        Book book = new Book();
        book.setId(patchBook.getId());
        book.setName(patchBook.getName());
        book.setAuthor(patchBook.getAuthor());
        book.setQuantity(patchBook.getQuantity());
        book.setPrice(patchBook.getPrice());
        return book;
    }

    public Book toModel(PutBook bookDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }


}
