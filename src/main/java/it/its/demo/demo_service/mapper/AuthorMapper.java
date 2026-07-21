package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.author.InnerBookDto;
import it.its.demo.demo_service.dto.author.ResAuthorDto;
import it.its.demo.demo_service.dto.author.ResInsertAuthorDto;
import it.its.demo.demo_service.model.Author;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuthorMapper {

//  Author -> AuthorDto
public ResAuthorDto toDto(Author author) {
    ResAuthorDto resAuthorDto = new ResAuthorDto();

    resAuthorDto.setId(author.getId());
    resAuthorDto.setName(author.getName());

    List<InnerBookDto> innerBookDtoList = author.getBooks().stream()
            .map(book -> new InnerBookDto(
                    book.getId(),
                    book.getName(),
                    book.getQuantity(),
                    book.getPrice()))
            .toList();

    resAuthorDto.setBooks(innerBookDtoList);

    return resAuthorDto;
}

//  AuthorDto -> Author
    public Author toModel(ResAuthorDto resAuthorDto) {

        Author author = new Author();

        author.setId(resAuthorDto.getId());
        author.setName(resAuthorDto.getName());

        return author;
    }

//  InsertAuthorDto -> Author
    public Author toModel(ResInsertAuthorDto resInsertAuthorDto) {

        Author author = new Author();

        author.setName(resInsertAuthorDto.getName());

        return author;
    }



}
