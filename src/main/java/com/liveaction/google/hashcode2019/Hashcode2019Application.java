package com.liveaction.google.hashcode2019;

import com.liveaction.google.hashcode2019.input.PizzaInput;
import com.liveaction.google.hashcode2019.model.Slice;
import com.liveaction.google.hashcode2019.model.SliceSolver;
import com.liveaction.google.hashcode2019.output.writer.OutputWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class Hashcode2019Application {

	public static void main(String[] args) throws IOException {
		String filePath = args[0];
		Path path = Paths.get(filePath);

		PizzaInput pizzaInput = PizzaInput.fromFile(path);

		SliceSolver solver = null; // implementation

		Collection<Slice> slices = solver.solve(pizzaInput.pizza, pizzaInput.minIngredients, pizzaInput.maxCells);

		new OutputWriter().writeOutput(slices);
	}
}
