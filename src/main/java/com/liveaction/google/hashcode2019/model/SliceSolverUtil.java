package com.liveaction.google.hashcode2019.model;

import java.util.stream.Stream;

public interface SliceSolverUtil {

    Stream<Slice> possiblesSlices(Pizza pizza, Position from, int maxSliceSize, int minIngredients);

    Position sliceMaxRange(Pizza pizza, Position from);
}
