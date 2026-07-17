package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.BookDto;
import it.its.demo.demo_service.dto.InsertBook;
import it.its.demo.demo_service.model.Book;
import org.springframework.stereotype.Component;

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
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }

}
