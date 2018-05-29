package com.deni.test.recipe.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Recipe {
    @Transient
    Logger logger = LoggerFactory.getLogger(Recipe.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    public void addCategories(Category category){
        categories.add(category);
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getPrepTime() {
        return this.prepTime;
    }

    public Integer getCookTime() {
        return this.cookTime;
    }

    public Integer getServings() {
        return this.servings;
    }

    public String getSource() {
        return this.source;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDirections() {
        return this.directions;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public Byte[] getImage() {
        return this.image;
    }

    public Notes getNotes() {
        return this.notes;
    }

    public Set<Ingredient> getIngredients() {
        logger.debug("Deni2 return ingredient");
        logger.debug("Deni2 size of ingredient" + this.ingredients.size());
        return this.ingredients;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public java.lang.String toString() {
        return "Recipe(id=" + this.id + ", description=" + this.description + ", prepTime=" + this.prepTime + ", cookTime=" + this.cookTime + ", servings=" + this.servings + ", source=" + this.source + ", url=" + this.url + ", directions=" + this.directions + ", difficulty=" + this.difficulty + ", image=" + java.util.Arrays.deepToString(this.image) + ", notes=" + this.notes + ", ingredients=" + this.ingredients + ", categories=" + this.categories + ")";
    }
}
