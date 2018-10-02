package com.deni.web.recipe.services;

import com.deni.web.recipe.commands.IngredientCommand;
import com.deni.web.recipe.model.Ingredient;

import java.util.List;

public interface IngredientService {

    IngredientCommand getIngredientCommand(long id);
    Ingredient saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteIngredient(long id);
    List<IngredientCommand> getAllIngredientCommands(long recipeId);
}
