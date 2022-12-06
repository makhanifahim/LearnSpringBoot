package com.example.RecipeApp.services;

import com.example.RecipeApp.domain.Recipe;
import com.example.RecipeApp.repositories.RecipeRepositories;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepositories recipeRepositories;

    public RecipeServiceImpl(RecipeRepositories recipeRepositories)
    {
        this.recipeRepositories = recipeRepositories;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet=new HashSet<>();
        recipeRepositories.findAll().iterator().forEachRemaining(recipeSet::add);
//        recipeRepositories.findAll();
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOption = recipeRepositories.findById(l);

        if(!recipeOption.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOption.get();
    }


}
