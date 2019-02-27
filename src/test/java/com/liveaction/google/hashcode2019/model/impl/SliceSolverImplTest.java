package com.liveaction.google.hashcode2019.model.impl;

import com.liveaction.google.hashcode2019.model.Ingredient;
import com.liveaction.google.hashcode2019.model.Pizza;
import com.liveaction.google.hashcode2019.model.Slice;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class SliceSolverImplTest {

    private SliceSolverImpl tested;

    @Before
    public void setUp() throws Exception {
        tested = new SliceSolverImpl();
    }

    @Test
    public void shouldSolveEasy() {
        Pizza pizza = Pizza.of(2, 2, Ingredient.TOMATO, Ingredient.TOMATO, Ingredient.MUSHROOM, Ingredient.MUSHROOM);
        System.out.println(pizza);
        Collection<Slice> solve = tested.solve(pizza, 1, 2);
    }
}