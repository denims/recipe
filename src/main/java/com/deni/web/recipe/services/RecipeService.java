package com.deni.web.recipe.services;

import com.deni.web.recipe.commands.RecipeCommand;
import com.deni.web.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe getRecipe(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipe);
    RecipeCommand getRecipeCommand(Long id);

    void deleteRecipe(Long Id);
    void saveRecipe(Recipe recipe);
}
