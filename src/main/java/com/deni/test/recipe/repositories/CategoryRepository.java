package com.deni.test.recipe.repositories;

import com.deni.test.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryName(String categoryName);
}
