package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.UnitOfMeasureCommand;
import com.deni.test.recipe.model.UnitOfMeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand,UnitOfMeasure> {

    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source==null)
            return null;
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(source.getId());
        unitOfMeasure.setUom(source.getUom());
        return unitOfMeasure;
    }
}
