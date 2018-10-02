package com.deni.web.recipe.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    public Long getId() {
        return this.id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public Set<Recipe> getRecipes() {
        return this.recipes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public java.lang.String toString() {
        return "Category(id=" + this.id + ", categoryName=" + this.categoryName + ", recipes=" + this.recipes + ")";
    }


}
