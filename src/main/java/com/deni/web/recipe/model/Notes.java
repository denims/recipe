package com.deni.web.recipe.model;

import javax.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String recipeNote;
    @OneToOne
    private Recipe recipe;

    @Override
    public String toString() {
        return recipeNote;
    }

    public Long getId() {
        return this.id;
    }

    public String getRecipeNote() {
        return this.recipeNote;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecipeNote(String recipeNote) {
        this.recipeNote = recipeNote;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
