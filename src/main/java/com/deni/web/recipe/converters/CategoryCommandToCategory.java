package com.deni.web.recipe.converters;

import com.deni.web.recipe.commands.CategoryCommand;
import com.deni.web.recipe.model.Category;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null)
            return null;
        Category category = new Category();
        category.setId(source.getId());
        category.setCategoryName(source.getCategoryName());

        return category;
    }
}
