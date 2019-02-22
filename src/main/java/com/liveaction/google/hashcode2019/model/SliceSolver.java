package com.liveaction.google.hashcode2019.model;

import java.util.Collection;

public interface SliceSolver {

    Collection<Slice> solve(Pizza pizza, int minIngredient, int maxCells);

}
