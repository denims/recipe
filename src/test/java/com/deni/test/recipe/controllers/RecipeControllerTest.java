package com.deni.test.recipe.controllers;

import com.deni.test.recipe.model.Recipe;
import com.deni.test.recipe.repositories.RecipeRepository;
import com.deni.test.recipe.services.RecipeService;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    RecipeController recipeController;
    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }
//    @Test
//    public void testIfGetRecipeIsCalledOnlyOnce(){
//        recipeController.getRecipe(model,ArgumentMatchers.anyLong());
//        Mockito.verify(recipeService,Mockito.times(1)).getRecipe(ArgumentMatchers.anyLong());
//    }
//    @Test
//    public void testIfRecipeIsreturned(){
//        assertEquals("recipe",recipeController.getRecipe(model,ArgumentMatchers.anyLong()));
//    }

    @Test
    public void testIfCorrectViewAndModelIsReturned() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipe(1L)).thenReturn(recipe);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/show/1")).andExpect(view().name("recipe/show")).
                andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));
    }
}