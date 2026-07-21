package it.its.demo.demo_service.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InnerBookDto {
    String id;
    String name;
    Integer quantity;
    Float price;

}
