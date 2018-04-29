package com.deni.test.recipe.controllers;

import com.deni.test.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndex(Model model){
        log.debug("Called index controller");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
