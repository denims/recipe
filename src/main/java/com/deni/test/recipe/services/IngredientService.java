package com.deni.test.recipe.services;

import com.deni.test.recipe.commands.IngredientCommand;
import com.deni.test.recipe.model.Ingredient;

import java.util.List;

public interface IngredientService {

    IngredientCommand getIngredientCommand(long id);
    Ingredient saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteIngredient(long id);
    List<IngredientCommand> getAllIngredientCommands(long recipeId);
}
