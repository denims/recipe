package com.deni.test.recipe.services;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.converters.RecipeCommandToRecipe;
import com.deni.test.recipe.converters.RecipeToRecipeCommand;
import com.deni.test.recipe.model.Recipe;
import com.deni.test.recipe.repositories.RecipeRepository;
import com.deni.test.recipe.exceptions.RecipeNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Logger log = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand,
                             RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public List<Recipe> getRecipes(){
        log.debug("getRecipes method call");
        List<Recipe> recipes = new ArrayList<>();
        for(Recipe recipe:recipeRepository.findAll()){
            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public Recipe getRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent())
            throw new RecipeNotFound("No recipes present");
        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand getRecipeCommand(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        log.debug("RecipeServiceImpl Is image present" + " " + recipe.get().getImage());
        if(!recipe.isPresent())
            throw new RecipeNotFound("No recipes present");
        return recipeToRecipeCommand.convert(recipe.get());
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
