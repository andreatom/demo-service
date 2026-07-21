package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.author.ResAuthorDto;
import it.its.demo.demo_service.dto.book.*;
import it.its.demo.demo_service.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    @Autowired
    AuthorMapper authorMapper;

    public ResBookDto toDto(Book book){
        ResBookDto resBookDto = new ResBookDto();
        InnerAuthorDto innerAuthorDto =
                new InnerAuthorDto(
                        book.getAuthor().getId(),
                        book.getAuthor().getName()
                );
        resBookDto.setId(book.getId());
        resBookDto.setName(book.getName());
        resBookDto.setAuthor(innerAuthorDto);
        resBookDto.setQuantity(book.getQuantity());
        resBookDto.setPrice(book.getPrice());
        return resBookDto;
    }

    public Book toModel(ReqInsertBook bookDto, ResAuthorDto resAuthorDto){
        Book book = new Book();
        //book.setId(UUID.randomUUID().toString());
        book.setName(bookDto.getName());
        book.setAuthor(authorMapper.toModel(resAuthorDto));
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }

    public Book toModel(String id, ReqPatchBookDto reqPatchBookDto, ResAuthorDto resAuthorDto){
        Book book = new Book();
        book.setId(id);
        book.setName(reqPatchBookDto.getName());
        book.setAuthor(authorMapper.toModel(resAuthorDto));
        book.setQuantity(reqPatchBookDto.getQuantity());
        book.setPrice(reqPatchBookDto.getPrice());
        return book;
    }

    public Book toModel(ReqReqPatchBookDtoWithId patchBook, ResAuthorDto resAuthorDto){
        Book book = new Book();
        book.setId(patchBook.getId());
        book.setName(patchBook.getName());
        book.setAuthor(authorMapper.toModel(resAuthorDto));
        book.setQuantity(patchBook.getQuantity());
        book.setPrice(patchBook.getPrice());
        return book;
    }

    public Book toModel(ReqPutBookDto bookDto, ResAuthorDto resAuthorDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(authorMapper.toModel(resAuthorDto));
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        return book;
    }


}
