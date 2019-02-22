package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public final class Pizza {

    public final Ingredient[][] cells;
    public final int row;
    public final int column;

    public Pizza(Ingredient[][] cells) {
        this.cells = cells;
        row = cells.length;
        column = cells[0].length;
    }

    public static Pizza of(int rows, int columns, Ingredient... ingredients) {
        List<List<Ingredient>> inputRows = Lists.partition(Lists.newArrayList(ingredients), rows);
        Ingredient[][] cells = new Ingredient[rows][];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Ingredient[] row = new Ingredient[columns];
                List<Ingredient> inputRow = inputRows.get(r);
                for (int i = 0; i < inputRow.size(); i++) {
                    row[i] = inputRow.get(i);
                }
                cells[r] = row;
            }
        }
        return new Pizza(cells);
    }

    public SliceValid isValid(Slice slice, int minIngredient, int maxCells) {
        if (slice.size() > maxCells) {
            return SliceValid.TOO_BIG;
        }
        if (slice.r2 >= row || slice.c2 >= column) {
            return SliceValid.OUT_OF_BOUND;
        }
        int nbMushrooms = 0;
        int nbTomatoes = 0;
        for (Ingredient ingredient : iterate(slice)) {
            switch (ingredient) {
                case TOMATO:
                    nbTomatoes++;
                    break;
                case MUSHROOM:
                    nbMushrooms++;
                    break;
                case EMPTY:
                    return SliceValid.EMPTY;
            }
        }
        if (nbMushrooms >= minIngredient && nbTomatoes >= minIngredient) {
            return SliceValid.VALID;
        } else {
            return SliceValid.NOT_ENOUGH_INGREDIENT;
        }
    }

    public Iterable<Ingredient> iterate(Slice slice) {
        return () -> IntStream.range(slice.r1, slice.r2 + 1)
                .mapToObj(row -> IntStream.range(slice.c1, slice.c2 + 1)
                        .mapToObj(column -> cells[row][column])
                ).flatMap(s -> s).iterator();
    }

    public Pizza cut(Slice slice) {
        Ingredient[][] newPizza = new Ingredient[cells.length][];
        for (int i = 0; i < newPizza.length; i++) {
            Ingredient[] column = new Ingredient[cells[i].length];
            System.arraycopy(cells[i], 0, column, 0, cells[i].length);
            newPizza[i] = column;
        }
        for (int r = slice.r1; r <= slice.r2; r++) {
            for (int c = slice.c1; c <= slice.c2; c++) {
                if (cells[r][c] == Ingredient.EMPTY) {
                    throw new IllegalStateException("cell is empty");
                }
                newPizza[r][c] = Ingredient.EMPTY;
            }
        }
        return new Pizza(newPizza);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        return Arrays.deepEquals(cells, pizza.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cells.length);
        if (cells.length > 0) {
            sb.append(" ");
            sb.append(cells[0].length);
            sb.append("\n");
            for (Ingredient[] cell : cells) {
                for (Ingredient ingredient : cell) {
                    sb.append(ingredient.repr);
                }
                sb.append("\n");
            }
        } else {
            sb.append("EMPTY");
        }
        return sb.toString();
    }

}
