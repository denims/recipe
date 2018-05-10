package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.IngredientCommand;
import com.deni.test.recipe.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source==null)
            return null;
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());

        if(source.getUnitOfMeasure()!=null){
            ingredientCommand.setUnitOfMeasureCommand(converter.convert(source.getUnitOfMeasure()));
        }

        return ingredientCommand;
    }
}
