package com.deni.test.recipe.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void saveImage(long recipeId, MultipartFile multipartFile);
}
