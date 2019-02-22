package com.liveaction.google.hashcode2019;

import com.google.common.collect.Lists;
import com.liveaction.google.hashcode2019.input.Input;
import com.liveaction.google.hashcode2019.output.writer.OutputWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Hashcode2019Application {

	public static void main(String[] args) throws IOException {
		String filePath = args[0];
		Path path = Paths.get(filePath);

		Input input = Input.fromFile(path);
		System.out.println(input);

		List<?> results = Lists.newArrayList();

		new OutputWriter().writeOutput(results);
	}
}
