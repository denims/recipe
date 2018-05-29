package com.deni.test.recipe.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Transient
    Logger logger = LoggerFactory.getLogger(Ingredient.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Ingredient() {
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        logger.debug("Deni2 Getting description");
        logger.debug("Deni2"+this.description);
        return this.description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

}
