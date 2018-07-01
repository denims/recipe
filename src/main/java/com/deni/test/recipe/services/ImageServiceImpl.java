package com.deni.test.recipe.services;

import com.deni.test.recipe.model.Recipe;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class ImageServiceImpl implements ImageService {

    private RecipeService recipeService;

    public ImageServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public void saveImage(long recipeId, MultipartFile multipartFile){
        Recipe recipe = recipeService.getRecipe(recipeId);
        Byte[] imageBytes;
        try {
            imageBytes = new Byte[multipartFile.getBytes().length];
            int pos=0;
            for(Byte imageByte : multipartFile.getBytes()){
                imageBytes[pos]=imageByte;
                pos++;
            }
            recipe.setImage(imageBytes);
            recipeService.saveRecipe(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
