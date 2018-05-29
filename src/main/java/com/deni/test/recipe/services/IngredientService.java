package com.deni.test.recipe.services;

import com.deni.test.recipe.model.Ingredient;

public interface IngredientService {

    Ingredient getIngredient(long id);
    Ingredient saveIngredient(Ingredient ingredient);
    void deleteIngredient(long id);
}
