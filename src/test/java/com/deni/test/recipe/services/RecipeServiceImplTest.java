package com.deni.test.recipe.services;

import com.deni.test.recipe.model.Recipe;
import com.deni.test.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);


    }

    @Test
    public void testIfRecipeServiceReturnsAllRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        recipes.add(new Recipe());
        recipes.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(1,recipeService.getRecipes().size());

    }
}