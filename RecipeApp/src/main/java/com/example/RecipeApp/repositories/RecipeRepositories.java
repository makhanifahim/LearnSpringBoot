package com.example.RecipeApp.repositories;

import com.example.RecipeApp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepositories extends CrudRepository<Recipe, Long> {

}
