package com.deni.web.recipe.controllers;

import com.deni.web.recipe.commands.RecipeCommand;
import com.deni.web.recipe.model.Recipe;
import com.deni.web.recipe.services.RecipeService;
import com.deni.web.recipe.exceptions.RecipeNotFound;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    private RecipeController recipeController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testIfGetRecipeIsCalledOnlyOnce() {
        recipeController.getRecipe(model, "1");
        Mockito.verify(recipeService, Mockito.times(1)).getRecipe(ArgumentMatchers.anyLong());
    }

    @Test
    public void testIfRecipeShowIsReturned() {
        assertEquals("recipe/show", recipeController.getRecipe(model, "1"));
    }

    @Test
    public void testIfRecipeFormIsReturned() {
        assertEquals("recipe/recipeform", recipeController.newRecipe(model));
    }

    @Test
    public void testIfCorrectViewAndModelIsReturnedForShow() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipe(1L)).thenReturn(recipe);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/1/show")).andExpect(view().name("recipe/show")).
                andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testIfCorrectViewAndModelIsReturnedForNew() throws Exception {
        mockMvc.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attribute("recipe", (instanceOf(RecipeCommand.class))));
    }

    @Test
    public void testIfCorrectViewAndModelIsReturnedForAddOrUpdate() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1"))

                .andExpect(status().is3xxRedirection()).andExpect(view()
                .name("redirect:/recipe/" + recipeCommand.getId() + "/show"));
    }

    @Test
    public void testIfModelAndViewIsReturnedForUpdateRecipe() throws Exception {
        mockMvc.perform(get("/recipe/1/update")).andExpect(status().isOk()).andExpect(view()
                .name("recipe/recipeform"));
    }
    @Test
    public void testIfCorrectModelAndViewIsReturnedForDelete() throws Exception {
        mockMvc.perform(get("/recipe/1/delete")).andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void givenNotPresentRecipeShouldSendNotFoundStatusAndErrorPage() throws Exception {
        when(recipeService.getRecipe(anyLong())).thenThrow(RecipeNotFound.class);

        mockMvc.perform(get("/recipe/1/show")).andExpect(view().name("404error"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenBadRecipeIdShouldSendBadRequestStatusAndErrorPage() throws Exception {

        mockMvc.perform(get("/recipe/asdf/show")).andExpect(view().name("400error"))
                .andExpect(status().isBadRequest());
    }
}