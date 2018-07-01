package com.deni.test.recipe.controllers;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.services.ImageService;
import com.deni.test.recipe.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private RecipeService recipeService;
    private ImageService imageService;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String uploadImage(@PathVariable String recipeId, Model model){
        model.addAttribute("recipeId", recipeId);
        return "recipe/imageupload";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String saveImage(@PathVariable String recipeId, @RequestParam("inputImage") MultipartFile multipartFile){
        imageService.saveImage(Long.parseLong(recipeId),multipartFile);
        return "redirect:/recipe/"+recipeId+"/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.getRecipeCommand(Long.valueOf(id));
        logger.debug("renderImageFromDB Got recipe command with id " + " " + recipeCommand.getId());
        logger.debug("Image in null " + (recipeCommand.getImage()));
        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;
            logger.debug("renderImageFromDB image not null");
            for (Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
