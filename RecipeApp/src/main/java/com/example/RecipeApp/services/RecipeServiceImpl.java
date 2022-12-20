package com.example.RecipeApp.services;

import com.example.RecipeApp.commands.RecipeCommand;
import com.example.RecipeApp.converters.RecipeCommandToRecipe;
import com.example.RecipeApp.converters.RecipeToRecipeCommand;
import com.example.RecipeApp.domain.Recipe;
import com.example.RecipeApp.repositories.RecipeRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepositories recipeRepositories;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepositories recipeRepositories, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand)
    {
        this.recipeRepositories = recipeRepositories;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepositories.save(detachedRecipe);

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }


    public void deleteById(Long idToDelete) {
        recipeRepositories.deleteById(idToDelete);
    }
}
