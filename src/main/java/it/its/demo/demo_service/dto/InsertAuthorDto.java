package it.its.demo.demo_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertAuthorDto {

    @NotBlank(message = "Author name cannot be null")
    private String name;
}
