package it.its.demo.demo_service.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Category not found. Id:" + id);
    }
}
