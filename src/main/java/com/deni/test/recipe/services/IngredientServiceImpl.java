package com.deni.test.recipe.services;

import com.deni.test.recipe.commands.IngredientCommand;
import com.deni.test.recipe.converters.IngredientCommandToIngredient;
import com.deni.test.recipe.converters.IngredientToIngredientCommand;
import com.deni.test.recipe.model.Ingredient;
import com.deni.test.recipe.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    IngredientRepository ingredientRepository;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    RecipeService recipeService;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 IngredientToIngredientCommand ingredientToIngredientCommand, RecipeService recipeService) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeService = recipeService;
    }

    public List<IngredientCommand> getAllIngredientCommands(long recipeId) {
        Set<Ingredient> ingredients = recipeService.getRecipe(recipeId).getIngredients();
        List<IngredientCommand> ingredientCommandList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ingredientCommandList.add(ingredientToIngredientCommand.convert(ingredient));
        }
        Collections.sort(ingredientCommandList, (o1, o2) -> {
            if (o1.getId() < o2.getId())
                return -1;
            else
                return 1;
        });
        return ingredientCommandList;
    }


    @Override
    public IngredientCommand getIngredientCommand(long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if(!ingredient.isPresent())
            throw new RuntimeException("Ingredient not present");
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient.get());
        return ingredientCommand;
    }

    public Ingredient saveIngredientCommand(IngredientCommand ingredientCommand) {
        Ingredient savedIngredient = ingredientRepository.save(ingredientCommandToIngredient.convert(ingredientCommand));
        return savedIngredient;
    }

    @Override
    public void deleteIngredient(long id) {
        ingredientRepository.deleteById(id);
    }
}
