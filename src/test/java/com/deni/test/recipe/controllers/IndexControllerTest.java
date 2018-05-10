package com.deni.test.recipe.controllers;

import com.deni.test.recipe.model.Recipe;
import com.deni.test.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;
    private IndexController indexController;

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
        verify(model,times(1)).addAttribute(eq("recipes"),anyList());
    }
    @Test
    public void testIfGerRecipeIsCalledOnlyOnce(){
        indexController.getIndex(model);
        verify(recipeService,times(1)).getRecipes();
    }
    @Test
    public void testIfIndexControllerReturnsCorrectStatusAndView() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }
    @Test
    public void testForIndexController() throws Exception {
        MockMvc indexControllerMock = MockMvcBuilders.standaloneSetup(indexController).build();
        indexControllerMock.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception {

        //given
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = indexController.getIndex(model);


        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        List<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}