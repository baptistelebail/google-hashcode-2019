package com.liveaction.google.hashcode2019.model;

public enum Ingredient {
    TOMATO('T'),
    MUSHROOM('M'),
    EMPTY(' ');

    public final char repr;

    Ingredient(char repr) {
        this.repr = repr;
    }

}
