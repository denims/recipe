package com.deni.test.recipe.controllers;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    public static final String RECIPE = "recipe";
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipe(Model model, @PathVariable Long id){
        model.addAttribute(RECIPE,recipeService.getRecipe(id));
        return "recipe/show";
    }
    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        RecipeCommand recipeCommand = new RecipeCommand();
        model.addAttribute(RECIPE,recipeCommand);
        return "recipe/recipeform";
    }
    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        Long recipeId = Long.parseLong(id);
        RecipeCommand recipeCommand = recipeService.getRecipeCommand(recipeId);
        model.addAttribute(RECIPE, recipeCommand);
        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String addOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/"+ savedRecipe.getId()+"/show";
    }
    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        Long idToDelete = Long.parseLong(id);
        recipeService.deleteRecipe(idToDelete);

        return "redirect:/";
    }
}
