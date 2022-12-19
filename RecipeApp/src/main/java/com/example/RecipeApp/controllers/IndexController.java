package com.example.RecipeApp.controllers;

import com.example.RecipeApp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({" ","/","/index"})
    public String getIndexPage(Model model)
    {
        model.addAttribute("recipes",recipeService.getRecipes());
        log.debug("Get in Index Page i am in the server");
        return "index";
    }

}
