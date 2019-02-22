package com.liveaction.google.hashcode2019.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SliceSolverTest {

    @Test
    public void shouldCreateAPizza() {
        Pizza of = Pizza.of(3, 3,
                Ingredient.MUSHROOM, Ingredient.TOMATO, Ingredient.TOMATO,
                Ingredient.MUSHROOM, Ingredient.TOMATO, Ingredient.TOMATO,
                Ingredient.MUSHROOM, Ingredient.TOMATO, Ingredient.MUSHROOM
        );
        assertThat(of.cells)
                .isEqualTo(new Ingredient[][]{
                        {Ingredient.MUSHROOM, Ingredient.TOMATO, Ingredient.TOMATO},
                        {Ingredient.MUSHROOM, Ingredient.TOMATO, Ingredient.TOMATO},
                        {Ingredient.MUSHROOM, Ingredient.TOMATO, Ingredient.MUSHROOM}
                });
    }
    
}