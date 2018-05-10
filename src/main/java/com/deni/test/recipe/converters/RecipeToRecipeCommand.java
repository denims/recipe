package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.model.Category;
import com.deni.test.recipe.model.Ingredient;
import com.deni.test.recipe.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        if(source.getIngredients()!= null && source.getIngredients().size()>0){
            for(Ingredient ingredient : source.getIngredients()){
                recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient));
            }
        }
        if(source.getCategories()!=null && source.getCategories().size()>0){
            for (Category category:source.getCategories()){
                recipeCommand.getCategories().add(categoryConverter.convert(category));
            }
        }
        return recipeCommand;
    }
}
