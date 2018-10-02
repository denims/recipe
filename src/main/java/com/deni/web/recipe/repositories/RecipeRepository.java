package com.deni.web.recipe.repositories;


import com.deni.web.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
