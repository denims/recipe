package com.deni.test.recipe.services;

import com.deni.test.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe getRecipe(Long id);
}
