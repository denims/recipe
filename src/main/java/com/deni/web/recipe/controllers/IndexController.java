package com.deni.web.recipe.controllers;

import com.deni.web.recipe.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndex(Model model){
        log.info("Deni1 Called index controller");
        log.debug("Deni2 Called index controller added all recipes");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
