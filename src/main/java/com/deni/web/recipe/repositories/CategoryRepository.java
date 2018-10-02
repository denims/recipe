package com.deni.web.recipe.repositories;

import com.deni.web.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryName(String categoryName);
}
