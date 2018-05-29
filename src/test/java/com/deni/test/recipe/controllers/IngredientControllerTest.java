package com.deni.test.recipe.controllers;

import com.deni.test.recipe.commands.IngredientCommand;
import com.deni.test.recipe.services.IngredientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    private List<IngredientCommand> ingredientCommandList = new ArrayList<>();
    private IngredientCommand ingredientCommand = new IngredientCommand();

    @Mock
    private IngredientService ingredientService;

    private IngredientController ingredientController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientController = new IngredientController(ingredientService);

    }
    @Test
    public void whenAllIngredientsCalled_ShouldReturnCorrectView() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        ingredientCommandList.add(new IngredientCommand());

        when(ingredientService.getAllIngredientCommands(anyLong())).thenReturn(ingredientCommandList);

        mvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/allingredients"))
                .andExpect(model().attributeExists("ingredients"));

    }

    @Test
    public void whenShowIngredientCalled_ShouldReturnCorrectView() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        ingredientCommandList.add(new IngredientCommand());

        when(ingredientService.getIngredientCommand(anyLong())).thenReturn(ingredientCommand);

        mvc.perform(get("/recipe/1/ingredients/1/show")).andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }

    @Test
    public void whenUpdateIngredientCalled_ShouldReturnCorrectView() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        ingredientCommandList.add(new IngredientCommand());

        when(ingredientService.getIngredientCommand(anyLong())).thenReturn(ingredientCommand);

        mvc.perform(get("/recipe/1/ingredients/1/update")).andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"));

    }
}