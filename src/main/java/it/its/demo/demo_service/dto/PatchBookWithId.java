package it.its.demo.demo_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatchBookWithId extends PatchBook{

    @NotBlank(message = "Id cannot be blank")
    String id;

    public PatchBookWithId(String name, String author, Integer quantity, Float price) {
        super(name, author, quantity, price);
    }
}
