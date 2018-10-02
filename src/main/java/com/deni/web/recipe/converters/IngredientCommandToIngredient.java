package com.deni.web.recipe.converters;

import com.deni.web.recipe.commands.IngredientCommand;
import com.deni.web.recipe.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private UnitOfMeasureCommandToUnitOfMeasure converter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
        this.converter = converter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null)
            return null;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());

        if(source.getUnitOfMeasureCommand()!=null){
            ingredient.setUnitOfMeasure(converter.convert(source.getUnitOfMeasureCommand()));
        }
        return ingredient;
    }
}
