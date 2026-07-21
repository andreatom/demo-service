package it.its.demo.demo_service.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResBookDto {
    String id;
    String name;
    InnerAuthorDto author;
    Integer quantity;
    Float price;
}
