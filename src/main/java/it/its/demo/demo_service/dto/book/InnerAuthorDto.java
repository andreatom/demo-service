package it.its.demo.demo_service.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerAuthorDto {
    private Integer id;
    private String name;
}
