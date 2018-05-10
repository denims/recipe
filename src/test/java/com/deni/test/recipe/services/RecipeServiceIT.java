package com.deni.test.recipe.services;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.converters.RecipeCommandToRecipe;
import com.deni.test.recipe.converters.RecipeToRecipeCommand;
import com.deni.test.recipe.model.Recipe;
import com.deni.test.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }
    @Test
    public void testIfGetRecipesReturnsSomeRecipes(){
        List<Recipe> recipes= recipeService.getRecipes();
        assertNotNull(recipes);
        assertEquals(Recipe.class,recipes.get(0).getClass());
    }

    @Test
    public void testIfGetRecipeReturnsSingleRecipe(){
        Recipe recipe = recipeService.getRecipe(1L);
        assertNotNull(recipe);
        assertEquals(Recipe.class,recipe.getClass());
    }
    @Test
    public void testIfRecipeIsSaved(){
        Recipe recipe = new Recipe();
        String description = "Test";
        recipe.setDescription(description);

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeToRecipeCommand.convert(recipe));

        assertEquals(description,savedRecipeCommand.getDescription());
    }
}
