package com.deni.web.recipe.converters;

import com.deni.web.recipe.commands.UnitOfMeasureCommand;
import com.deni.web.recipe.model.UnitOfMeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

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
