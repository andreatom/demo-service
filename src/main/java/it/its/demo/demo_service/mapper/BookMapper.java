package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.*;
import it.its.demo.demo_service.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookMapper {

    @Autowired
    AuthorMapper authorMapper;

    public BookDto toDto(Book book){
        BookDto bookDto = new BookDto();
        AuthorWithoutBooksDto authorWithoutBooksDto =
                new AuthorWithoutBooksDto(
                        book.getAuthor().getId(),
                        book.getAuthor().getName()
                );
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(authorWithoutBooksDto);
        bookDto.setQuantity(book.getQuantity());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    public Book toModel(InsertBook bookDto, AuthorDto authorDto){
        Book book = new Book();
        //book.setId(UUID.randomUUID().toString());
        book.setName(bookDto.getName());
        book.setAuthor(authorMapper.toModel(authorDto));
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }

    public Book toModel(String id, PatchBook patchBook, AuthorDto authorDto){
        Book book = new Book();
        book.setId(id);
        book.setName(patchBook.getName());
        book.setAuthor(authorMapper.toModel(authorDto));
        book.setQuantity(patchBook.getQuantity());
        book.setPrice(patchBook.getPrice());
        return book;
    }

    public Book toModel(PatchBookWithId patchBook, AuthorDto authorDto){
        Book book = new Book();
        book.setId(patchBook.getId());
        book.setName(patchBook.getName());
        book.setAuthor(authorMapper.toModel(authorDto));
        book.setQuantity(patchBook.getQuantity());
        book.setPrice(patchBook.getPrice());
        return book;
    }

    public Book toModel(PutBook bookDto, AuthorDto authorDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(authorMapper.toModel(authorDto));
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }


}
