package it.its.demo.demo_service.dto.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqPutBookDto extends ReqInsertBook {

    @NotBlank(message = "Id cannot be blank")
    String id;

    public ReqPutBookDto(String name, Integer author, Integer quantity, Float price) {
        super(name, author, quantity, price);
    }
}
