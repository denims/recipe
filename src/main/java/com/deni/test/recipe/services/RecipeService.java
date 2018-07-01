package com.deni.test.recipe.services;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe getRecipe(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipe);
    RecipeCommand getRecipeCommand(Long id);

    void deleteRecipe(Long Id);
    void saveRecipe(Recipe recipe);
}
