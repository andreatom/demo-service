package it.its.demo.demo_service.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class PatchBook {

    String name;
    String author;
    @Positive(message = "Quantity cannot be negative")
    Integer quantity;

}
