package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.AuthorDto;
import it.its.demo.demo_service.dto.BookWithoutAuthorDto;
import it.its.demo.demo_service.dto.InsertAuthorDto;
import it.its.demo.demo_service.model.Author;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuthorMapper {

//  Author -> AuthorDto
public AuthorDto toDto(Author author) {
    AuthorDto authorDto = new AuthorDto();

    authorDto.setId(author.getId());
    authorDto.setName(author.getName());

    List<BookWithoutAuthorDto> bookWithoutAuthorDtoList = author.getBooks().stream()
            .map(book -> new BookWithoutAuthorDto(
                    book.getId(),
                    book.getName(),
                    book.getQuantity(),
                    book.getPrice()))
            .toList();

    authorDto.setBooks(bookWithoutAuthorDtoList);

    return authorDto;
}

//  AuthorDto -> Author
    public Author toModel(AuthorDto authorDto) {

        Author author = new Author();

        author.setId(authorDto.getId());
        author.setName(authorDto.getName());

        return author;
    }

//  InsertAuthorDto -> Author
    public Author toModel(InsertAuthorDto insertAuthorDto) {

        Author author = new Author();

        author.setName(insertAuthorDto.getName());

        return author;
    }



}
