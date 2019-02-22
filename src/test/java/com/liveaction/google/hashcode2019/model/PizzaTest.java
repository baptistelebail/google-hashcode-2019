package com.liveaction.google.hashcode2019.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.liveaction.google.hashcode2019.model.Ingredient.*;
import static com.liveaction.google.hashcode2019.model.Ingredient.EMPTY;
import static org.junit.Assert.*;

public class PizzaTest {

    @Test
    public void shouldCutPizza() {
        Pizza start = new Pizza(new Ingredient[][]{
                new Ingredient[]{TOMATO, TOMATO, TOMATO, TOMATO, TOMATO},
                new Ingredient[]{TOMATO, MUSHROOM, MUSHROOM, MUSHROOM, TOMATO},
                new Ingredient[]{TOMATO, TOMATO, TOMATO, TOMATO, TOMATO}
        });
        Pizza duplicate = new Pizza(new Ingredient[][]{
                new Ingredient[]{TOMATO, TOMATO, TOMATO, TOMATO, TOMATO},
                new Ingredient[]{TOMATO, MUSHROOM, MUSHROOM, MUSHROOM, TOMATO},
                new Ingredient[]{TOMATO, TOMATO, TOMATO, TOMATO, TOMATO}
        });
        Pizza actual = start.cut(new Slice(0, 0, 2, 1));
        Pizza expected = new Pizza(new Ingredient[][]{
                new Ingredient[]{EMPTY, EMPTY, TOMATO, TOMATO, TOMATO},
                new Ingredient[]{EMPTY, EMPTY, MUSHROOM, MUSHROOM, TOMATO},
                new Ingredient[]{EMPTY, EMPTY, TOMATO, TOMATO, TOMATO}
        });
        Assertions.assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(start).isEqualTo(duplicate);
    }

    @Test
    public void shouldNotCutPizza() {
        Pizza start = new Pizza(new Ingredient[][]{
                new Ingredient[]{TOMATO, TOMATO, TOMATO, TOMATO, TOMATO},
                new Ingredient[]{TOMATO, MUSHROOM, EMPTY, MUSHROOM, TOMATO},
                new Ingredient[]{TOMATO, TOMATO, TOMATO, TOMATO, TOMATO}
        });
        Assertions.assertThatThrownBy(() -> start.cut(new Slice(0, 0, 2, 2))).hasMessage("cell is empty");

    }
}