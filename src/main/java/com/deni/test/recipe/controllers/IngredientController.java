package com.deni.test.recipe.controllers;

import com.deni.test.recipe.commands.IngredientCommand;
import com.deni.test.recipe.converters.IngredientCommandToIngredient;
import com.deni.test.recipe.converters.IngredientToIngredientCommand;
import com.deni.test.recipe.model.Ingredient;
import com.deni.test.recipe.services.IngredientService;
import com.deni.test.recipe.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class IngredientController {
    Logger logger = LoggerFactory.getLogger(IngredientController.class);
    RecipeService recipeService;
    IngredientService ingredientService;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                IngredientToIngredientCommand ingredientToIngredientCommand,
                                IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String getAllIngredients(@PathVariable String id, Model model) {
        logger.debug("inside getAllIngredients");
        Long recipeId = Long.parseLong(id);
        List<IngredientCommand> ingredientCommandList = new ArrayList<>();
        for (Ingredient ingredient : recipeService.getRecipe(recipeId).getIngredients()) {
            ingredientCommandList.add(ingredientToIngredientCommand.convert(ingredient));
        }
        Collections.sort(ingredientCommandList, (o1, o2) -> {
            if (o1.getId() < o2.getId())
                return -1;
            else
                return 1;
        });
        model.addAttribute("ingredients", ingredientCommandList);
        logger.debug("returning from getAllIngredients");
        return "/recipe/ingredient/allingredients";
    }

    @GetMapping("/recipe/{rid}/ingredients/{id}/show")
    public String showIngredient(@PathVariable String rid, @PathVariable String id, Model model) {
        logger.debug("inside showIngredient");
        long ingredientId = Long.parseLong(id);
        model.addAttribute("ingredient", ingredientToIngredientCommand.convert(ingredientService.getIngredient(ingredientId)));
        logger.debug("returning from showIngredient");
        return "/recipe/ingredient/show";
    }

    @GetMapping("/recipe/{rid}/ingredients/{id}/update")
    public String updateIngredient(@PathVariable String rid, @PathVariable String id, Model model) {
        logger.debug("inside updateIngredient");
        int ingredientId = Integer.parseInt(id);
        model.addAttribute("ingredient", ingredientToIngredientCommand.convert(ingredientService.getIngredient(ingredientId)));
        logger.debug("returning from showIngredient");
        return "/recipe/ingredient/ingredientform";
    }

    @PostMapping("/ingredient")
    public String addOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        Ingredient ingredientToSave = ingredientCommandToIngredient.convert(ingredientCommand);
        Ingredient savedIngredient = ingredientService.saveIngredient(ingredientToSave);

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
