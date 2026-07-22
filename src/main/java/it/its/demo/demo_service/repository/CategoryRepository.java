package it.its.demo.demo_service.repository;

import it.its.demo.demo_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
