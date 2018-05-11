package com.deni.test.recipe.controllers;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String getRecipe(Model model, @PathVariable Long id){
        model.addAttribute("recipe",recipeService.getRecipe(id));
        return "recipe/show";
    }
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        RecipeCommand recipeCommand = new RecipeCommand();
        model.addAttribute("recipe",recipeCommand);
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String addOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/"+ savedRecipe.getId();
    }
}
