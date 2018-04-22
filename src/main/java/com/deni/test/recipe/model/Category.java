package com.deni.test.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Recipe> recipes;
}
