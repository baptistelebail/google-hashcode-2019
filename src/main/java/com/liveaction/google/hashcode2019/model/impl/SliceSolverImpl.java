package com.liveaction.google.hashcode2019.model.impl;

import com.google.common.collect.ImmutableList;
import com.liveaction.google.hashcode2019.model.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public final class SliceSolverImpl implements SliceSolver {
    private SliceSolverUtil util = new SliceSolverUtilImpl();
    ;
    //    @Override
//    public Collection<Slice> solve(Pizza pizza, int minIngredient, int maxCells) {
//        for (int i = 0; i < pizza.rows; i++) {
//            Slice slice = new Slice(0, 0, i, i);
//            if (pizza.isValid(slice, minIngredient, maxCells) == SliceValid.VALID) {
//                return ImmutableList.of(slice);
//            }
//        }
//        return ImmutableList.of();
//    }

    @Override
    public Collection<Slice> solve(Pizza pizza, int minIngredient, int maxCells) {
        Collection<Slice> slices = recursiveSolver(pizza, minIngredient, maxCells, new Position(0, 0))
                .max(Comparator.comparingLong(this::sizes)).get();
        System.out.println("Solution is : "+slices);
        return slices;
    }

    public Stream<Collection<Slice>> recursiveSolver(Pizza pizza, int minIngredient, int maxCells, Position from) {
        return util.possiblesSlices(pizza, from, maxCells, minIngredient)
                .flatMap(slice -> {
                    Pizza cutPizza = pizza.cut(slice);
                    Optional<Position> nextPosition = getNextPosition(cutPizza, from);
                    return nextPosition.map(position -> recursiveSolver(cutPizza, minIngredient, maxCells, position)
                            .map(slices -> ImmutableList.<Slice>builder()
                                    .addAll(slices)
                                    .add(slice)
                                    .build())).orElse(Stream.of(ImmutableList.of(slice)));
                });
    }

    public Optional<Position> getNextPosition(Pizza pizza, Position position) {
        int row = position.row;
        int column = position.column;
        while (pizza.ingredient(row, column) == Ingredient.EMPTY) {
            if (column < pizza.columns-1) {
                column++;
            } else {
                column = 0;
                if (row < pizza.rows-1) {
                    row++;
                } else {
                    return Optional.empty();
                }
            }
        }
        return Optional.of(new Position(row, column));

    }

    private long sizes(Collection<Slice> slices) {
        return slices.stream()
                .mapToLong(Slice::size)
                .sum();
    }

}
