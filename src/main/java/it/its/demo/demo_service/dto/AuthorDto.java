package it.its.demo.demo_service.dto;

import it.its.demo.demo_service.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {

    Integer id;
    String name;
    List<Book> books;

}
