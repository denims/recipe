package com.deni.test.recipe.controllers;

import com.deni.test.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testIfIndexControllerIsReturningIndex(){
        assertEquals("index",indexController.getIndex(model));
    }
    @Test
    public void testIfAddAttributeIsCalledOnlyOnce(){
        indexController.getIndex(model);
        verify(model,Mockito.times(1)).addAttribute(eq("recipes"),anyList());
    }
    @Test
    public void testIfGerRecipeIsCalledOnlyOnce(){
        indexController.getIndex(model);
        verify(recipeService,Mockito.times(1)).getRecipes();
    }
    @Test
    public void testIfIndexControllerReturnsCorrectStatusAndView() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

}