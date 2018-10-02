package com.deni.web.recipe.repositories;

import com.deni.web.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
