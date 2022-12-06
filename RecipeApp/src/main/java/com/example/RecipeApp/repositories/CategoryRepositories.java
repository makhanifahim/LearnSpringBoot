package com.example.RecipeApp.repositories;

import com.example.RecipeApp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepositories extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
