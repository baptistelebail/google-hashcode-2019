package com.liveaction.google.hashcode2019.model;

import java.util.Arrays;

public final class Pizza {

    public final Ingredient[][] cells;

    public Pizza(Ingredient[][] cells) {
        this.cells = cells;
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
                cells[r][c] = Ingredient.EMPTY;
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
        final StringBuilder sb = new StringBuilder("Pizza{");
        sb.append("cells=").append(Arrays.toString(cells));
        sb.append('}');
        return sb.toString();
    }
}
