package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.AuthorDto;
import it.its.demo.demo_service.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(author.getId());
        authorDto.setName(author.getName());

        return authorDto;
    }

    public Author toModel(AuthorDto authorDto) {

        Author author = new Author();

        author.setId(authorDto.getId());
        author.setName(authorDto.getName());

        return author;
    }



}
