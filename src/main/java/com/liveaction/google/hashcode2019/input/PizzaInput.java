package com.liveaction.google.hashcode2019.input;

import com.google.common.base.MoreObjects;
import com.liveaction.google.hashcode2019.model.Ingredient;
import com.liveaction.google.hashcode2019.model.Pizza;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class PizzaInput {

    public int minIngredients;
    public int maxCells;
    public Pizza pizza;

    public PizzaInput(int minIngredients, int maxCells, Pizza pizza) {
        this.minIngredients = minIngredients;
        this.maxCells = maxCells;
        this.pizza = pizza;
    }

    public static PizzaInput fromFile(Path path) throws IOException {
        int nbRowsFromFile;
        int nbColumnsFromFile;
        int minIngredientsFromFile;
        int maxCellsFromFile;
        List<String> lines = Files.readAllLines(path);
        int inputSize = lines.size();

        String constraintLine = lines.get(0);
        String[] constraints = constraintLine.split(" ");
        nbRowsFromFile = Integer.parseInt(constraints[0]);
        nbColumnsFromFile = Integer.parseInt(constraints[1]);
        minIngredientsFromFile = Integer.parseInt(constraints[2]);
        maxCellsFromFile = Integer.parseInt(constraints[3]);

        Ingredient[][] ingredients = new Ingredient[nbRowsFromFile][nbColumnsFromFile];

        for (int i = 1; i < inputSize; i++) {
            String line = lines.get(i);

            for (int j = 0; j < line.length(); j++) {
                char ingredient = line.charAt(j);

                ingredients[i - 1][j] = 'M' == ingredient ? Ingredient.MUSHROOM : Ingredient.TOMATO;
            }
        }

        return new PizzaInput(minIngredientsFromFile, maxCellsFromFile, new Pizza(ingredients));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("minIngredients", minIngredients)
                .add("maxCells", maxCells)
                .add("pizza", pizza)
                .toString();
    }
}