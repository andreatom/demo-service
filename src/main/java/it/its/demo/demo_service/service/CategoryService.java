package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.category.ReqInsertCategoryDto;
import it.its.demo.demo_service.dto.category.ResCategoryDto;
import it.its.demo.demo_service.mapper.CategoryMapper;
import it.its.demo.demo_service.model.Category;
import it.its.demo.demo_service.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public ResCategoryDto insert(ReqInsertCategoryDto reqInsertCategoryDto) {
        Category category = categoryMapper.toModel(reqInsertCategoryDto);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    public List<ResCategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }
}
