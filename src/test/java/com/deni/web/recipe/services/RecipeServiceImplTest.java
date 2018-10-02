package com.deni.web.recipe.services;

import com.deni.web.recipe.converters.RecipeCommandToRecipe;
import com.deni.web.recipe.converters.RecipeToRecipeCommand;
import com.deni.web.recipe.model.Recipe;
import com.deni.web.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand,recipeCommandToRecipe);
    }

    @Test
    public void testIfRecipeServiceReturnsAllRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        recipes.add(new Recipe());
        recipes.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(2,recipeService.getRecipes().size());
    }
    @Test
    public void testIfFindAllIsCalledOnlyOnce(){
        recipeService.getRecipes();
        verify(recipeRepository,times(1)).findAll();
    }
    @Test
    public void testIfDeleteIsCalledOnlyOnce(){
        recipeService.deleteRecipe(any());

        verify(recipeRepository,times(1)).deleteById(any());
    }
}