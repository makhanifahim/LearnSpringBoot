package com.example.RecipeApp.services;

import com.example.RecipeApp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    public Recipe findById(Long l);
}
