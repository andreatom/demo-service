package it.its.demo.demo_service.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerCategoryDto {
    private Integer id;
    private String name;
}
