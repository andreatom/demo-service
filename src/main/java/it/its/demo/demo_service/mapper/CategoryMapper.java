package it.its.demo.demo_service.mapper;

import it.its.demo.demo_service.dto.category.ReqInsertCategoryDto;
import it.its.demo.demo_service.dto.category.ResCategoryDto;
import it.its.demo.demo_service.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toModel (ReqInsertCategoryDto reqInsertCategoryDto) {
        Category category = new Category();
        category.setName(reqInsertCategoryDto.getName());
        return category;
    }

    public ResCategoryDto toDto (Category category){
        ResCategoryDto resCategoryDto = new ResCategoryDto();
        resCategoryDto.setName(category.getName());
        return resCategoryDto;
    }
}
