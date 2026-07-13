package it.its.demo.demo_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InsertBook {

    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotBlank(message = "Author cannot be blank")
    String author;

    @NotNull(message = "Quantity cannot be null or negative")
    @Positive(message = "Quantity cannot be null or negative")
    Integer quantity;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price cannot be negative")
    Float price;

}
