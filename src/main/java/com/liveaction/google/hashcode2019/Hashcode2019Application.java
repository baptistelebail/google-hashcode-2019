package com.liveaction.google.hashcode2019;

import com.liveaction.google.hashcode2019.input.PizzaInput;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hashcode2019Application {

	public static void main(String[] args) throws IOException {
		String filePath = args[0];
		Path path = Paths.get(filePath);

		PizzaInput pizzaInput = PizzaInput.fromFile(path);
	}
}
