package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.CategoryCommand;
import com.deni.test.recipe.commands.IngredientCommand;
import com.deni.test.recipe.commands.RecipeCommand;
import com.deni.test.recipe.converters.CategoryCommandToCategory;
import com.deni.test.recipe.converters.IngredientCommandToIngredient;
import com.deni.test.recipe.converters.NotesCommandToNotes;
import com.deni.test.recipe.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }


    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null)
            return null;
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        if(source.getNotes()!=null)
            recipe.setNotes(notesConverter.convert(source.getNotes()));
        if(source.getIngredients()!= null && source.getIngredients().size()>0){
            for(IngredientCommand ingredientCommand : source.getIngredients()){
                recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand));
            }
        }
        if(source.getCategories()!=null && source.getCategories().size()>0){
            for (CategoryCommand categoryCommand:source.getCategories()){
                recipe.getCategories().add(categoryConverter.convert(categoryCommand));
            }
        }
        return recipe;
    }
}
