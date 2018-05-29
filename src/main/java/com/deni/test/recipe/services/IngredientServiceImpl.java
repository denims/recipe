package com.deni.test.recipe.services;

import com.deni.test.recipe.model.Ingredient;
import com.deni.test.recipe.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient getIngredient(long id) {
        Optional<Ingredient> ingredient= ingredientRepository.findById(id);
        return ingredient.get();
    }

    public Ingredient saveIngredient(Ingredient ingredient){
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return savedIngredient;
    }

    @Override
    public void deleteIngredient(long id) {
        ingredientRepository.deleteById(id);
    }
}
