package it.its.demo.demo_service.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResInsertAuthorDto {
    @NotBlank(message = "Author name cannot be null")
    private String name;
}
