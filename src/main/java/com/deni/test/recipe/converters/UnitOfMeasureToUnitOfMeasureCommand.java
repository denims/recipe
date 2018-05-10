package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.UnitOfMeasureCommand;
import com.deni.test.recipe.model.UnitOfMeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {

    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source==null)
            return null;
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(source.getId());
        unitOfMeasureCommand.setUom(source.getUom());
        return unitOfMeasureCommand;
    }
}
