package com.liveaction.google.hashcode2019.model.impl;

import com.google.common.collect.Lists;
import com.liveaction.google.hashcode2019.model.*;

import java.util.List;
import java.util.stream.Stream;

public final class SliceSolverUtilImpl implements SliceSolverUtil {
    @Override
    public Stream<Slice> possiblesSlices(Pizza pizza, Position from, int maxSliceSize, int minIngredients) {
        Position position = sliceMaxRange(pizza, from);
        int maxRows = position.row;
        int maxColumns = position.column;

        List<Slice> slices = Lists.newArrayList();
        int row = 0;
        int column = 0;
        while (row <= maxRows && size(row, column) <= maxSliceSize) {
            while (column <= maxColumns && size(row, column) <= maxSliceSize) {
                Slice slice = new Slice(from, new Position(from.row + row, from.column + column));
                if (pizza.isValid(slice, minIngredients, maxSliceSize) == SliceValid.VALID) {
                    slices.add(slice);
                }
                column++;
            }
            column = 0;
            row++;
        }

        return slices.stream();
    }

    private int size(int row, int column) {
        return (row + 1) * (column + 1);
    }

    @Override
    public Position sliceMaxRange(Pizza pizza, Position from) {
        int maxRow = 1;
        while (from.row + maxRow < pizza.rows && pizza.ingredient(from.row + maxRow, from.column) != Ingredient.EMPTY) {
            maxRow++;
        }
        maxRow--;
        int maxColumn = 1;
        while (from.row + maxColumn < pizza.rows && pizza.ingredient(from.row + maxColumn, from.column) != Ingredient.EMPTY) {
            maxColumn++;
        }
        maxColumn--;
        return new Position(maxRow, maxColumn);
    }
}
