package com.deni.web.recipe.controllers;

import com.deni.web.recipe.commands.IngredientCommand;
import com.deni.web.recipe.model.Ingredient;
import com.deni.web.recipe.services.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
    private Logger logger = LoggerFactory.getLogger(IngredientController.class);
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String getAllIngredients(@PathVariable String id, Model model) {
        logger.debug("inside getAllIngredients");
        Long recipeId = Long.parseLong(id);
        model.addAttribute("ingredients", ingredientService.getAllIngredientCommands(recipeId));
        logger.debug("returning from getAllIngredients");
        return "/recipe/ingredient/allingredients";
    }

    @GetMapping("/recipe/{rid}/ingredients/{id}/show")
    public String showIngredient(@PathVariable String rid, @PathVariable String id, Model model) {
        logger.debug("inside showIngredient");
        long ingredientId = Long.parseLong(id);
        model.addAttribute("ingredient", ingredientService.getIngredientCommand(ingredientId));
        logger.debug("returning from showIngredient");
        return "/recipe/ingredient/show";
    }

    @GetMapping("/recipe/{rid}/ingredients/{id}/update")
    public String updateIngredient(@PathVariable String rid, @PathVariable String id, Model model) {
        logger.debug("inside updateIngredient");
        int ingredientId = Integer.parseInt(id);
        model.addAttribute("ingredient", ingredientService.getIngredientCommand(ingredientId));
        logger.debug("returning from showIngredient");
        return "/recipe/ingredient/ingredientform";
    }

    @PostMapping("/ingredient")
    public String addOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {

        Ingredient savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedIngredient.getRecipe().getId() + "/ingredients/" + savedIngredient.getId() + "/show";
    }

    @GetMapping("/recipe/{rid}/ingredients/{id}/delete")
    public String deleteIngredient(@PathVariable String rid, @PathVariable String id) {
        logger.debug("inside deleteIngredient");
        long ingredientId = Long.parseLong(id);
        ingredientService.deleteIngredient(ingredientId);
        logger.debug("returning from deleteIngredient");
        return "redirect:/recipe/" + rid + "/ingredients/";
    }


}
