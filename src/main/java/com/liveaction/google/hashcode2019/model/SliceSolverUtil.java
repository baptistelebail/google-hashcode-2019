package com.liveaction.google.hashcode2019.model;

import java.util.Optional;
import java.util.stream.Stream;

public interface SliceSolverUtil {

   Optional<Stream<Slice>> possiblesSlices(Pizza pizza, Position from, int maxSliceSize, int minIngredients);

    Position sliceMaxRange(Pizza pizza, Position from);
}
