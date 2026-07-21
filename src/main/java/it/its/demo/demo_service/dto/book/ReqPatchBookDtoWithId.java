package it.its.demo.demo_service.dto.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqPatchBookDtoWithId extends ReqPatchBookDto {

    @NotBlank(message = "Id cannot be blank")
    String id;

    public ReqPatchBookDtoWithId(String name, Integer author, Integer quantity, Float price) {
        super(name, author, quantity, price);
    }
}
