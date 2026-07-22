package it.its.demo.demo_service.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqInsertBook {

    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotNull(message = "Author cannot be null")
    @Positive(message = "Author cannot be null or negative")
    Integer author;

    @NotNull(message = "Quantity cannot be null or negative")
    @Positive(message = "Quantity cannot be null or negative")
    Integer quantity;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price cannot be negative")
    Float price;

    @NotEmpty(message = "Categories cannot be empty")
    List<String> categories;

}
