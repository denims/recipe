package com.deni.web.recipe.converters;

import com.deni.web.recipe.commands.CategoryCommand;
import com.deni.web.recipe.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if(source==null)
            return null;
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setCategoryName(source.getCategoryName());
        return categoryCommand;
    }
}
