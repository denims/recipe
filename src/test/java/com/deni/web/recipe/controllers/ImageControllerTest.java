package com.deni.web.recipe.controllers;

import com.deni.web.recipe.services.ImageService;
import com.deni.web.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    private ImageController imageController;
    @Mock
    private ImageService imageService;

    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() {
        initMocks(this);
        imageController = new ImageController(recipeService,imageService);
    }

    @Test
    public void givenRecipeIDImage_ShouldReturnImageUpload_WhenImageControllerIsCalledWithGet() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(view().name("recipe/imageupload"))
                .andExpect(model().attributeExists("recipeId"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRecipeIDImage_ShouldRedirectToRecipe_WhenImageControllerIsCalledWithPost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

        MockMultipartFile multipartFile =
                new MockMultipartFile("inputImage", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));


    }

}