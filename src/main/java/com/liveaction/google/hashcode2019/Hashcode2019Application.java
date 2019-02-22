package com.liveaction.google.hashcode2019;

import com.liveaction.google.hashcode2019.input.Input;
import com.liveaction.google.hashcode2019.output.writer.OutputWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hashcode2019Application {

	public static void main(String[] args) throws IOException {
		String filePath = args[0];
		Path path = Paths.get(filePath);

		Input pizzaInput = Input.fromFile(path);
		System.out.println(pizzaInput);

		new OutputWriter().writeOutput(slices);
	}
}
