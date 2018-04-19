package com.deni.test.recipe.services;

import com.deni.test.recipe.model.Recipe;
import com.deni.test.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        for(Recipe recipe:recipeRepository.findAll()){
            recipes.add(recipe);
        }
        return recipes;
    }
}
