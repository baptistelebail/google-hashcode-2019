package com.liveaction.google.hashcode2019.model.impl;

import com.google.common.collect.ImmutableList;
import com.liveaction.google.hashcode2019.model.Pizza;
import com.liveaction.google.hashcode2019.model.Slice;
import com.liveaction.google.hashcode2019.model.SliceSolver;
import com.liveaction.google.hashcode2019.model.SliceValid;

import java.util.Collection;

public final class SliceSolverImpl implements SliceSolver {
    @Override
    public Collection<Slice> solve(Pizza pizza, int minIngredient, int maxCells) {
        for (int i = 0; i < pizza.row; i++) {
            Slice slice = new Slice(0, 0, i, i);
            if (pizza.isValid(slice, minIngredient, maxCells) == SliceValid.VALID) {
                return ImmutableList.of(slice);
            }
        }
        return ImmutableList.of();
    }

}
