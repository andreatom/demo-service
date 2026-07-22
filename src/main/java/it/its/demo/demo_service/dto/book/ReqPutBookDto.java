package it.its.demo.demo_service.dto.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReqPutBookDto extends ReqInsertBook {

    @NotBlank(message = "Id cannot be blank")
    String id;

    public ReqPutBookDto(String name, Integer author, Integer quantity, Float price) {
        super(name, author, quantity, price);
    }
}
