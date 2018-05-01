package com.deni.test.recipe.controllers;

import com.deni.test.recipe.services.RecipeService;
import com.deni.test.recipe.services.RecipeServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe")
    public String getRecipe(Model model, @RequestParam(value = "id") Long id){
        model.addAttribute("recipe",recipeService.getRecipe(id));
        return "recipe";
    }
}
