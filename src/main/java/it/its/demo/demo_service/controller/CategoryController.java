package it.its.demo.demo_service.controller;

import it.its.demo.demo_service.dto.category.ReqInsertCategoryDto;
import it.its.demo.demo_service.dto.category.ResCategoryDto;
import it.its.demo.demo_service.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public ResCategoryDto insert(
            @Valid @RequestBody ReqInsertCategoryDto reqInsertCategoryDto
            ) {
        return categoryService.insert(reqInsertCategoryDto);
    }

    @GetMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public List<ResCategoryDto> findAll() {
        return categoryService.findAll();
    }
}
