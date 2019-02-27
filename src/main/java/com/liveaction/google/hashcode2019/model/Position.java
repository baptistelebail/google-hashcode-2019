package com.liveaction.google.hashcode2019.model;

public class Position {
    public final int row;
    public final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(int row, int column) {
        return new Position(row, column);
    }
}
