package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.CategoryCommand;
import com.deni.test.recipe.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    private CategoryCommandToCategory categoryCommandToCategory;
    private Long id = 1L;
    private String description = "category name";

    @Before
    public void setUp() throws Exception {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }
    @Test
    public void testIfNullIsReturnedForNullSource(){
        assertNull(categoryCommandToCategory.convert(null));
    }
    @Test
    public void testIfEmptyObjectIsReturnedForEmptySource(){
        CategoryCommand categoryCommand = new CategoryCommand();
        assertNotNull(categoryCommandToCategory.convert(categoryCommand));
    }
    @Test
    public void testIfCategoryCommandIsConvertedToCategory(){
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(id);
        categoryCommand.setCategoryName(description);

        Category category = categoryCommandToCategory.convert(categoryCommand);
        assertEquals(id, category.getId());
        assertEquals(description,category.getCategoryName());
    }

}