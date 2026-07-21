package it.its.demo.demo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookWithoutAuthorDto {
    String id;
    String name;
    Integer quantity;
    Float price;

}
