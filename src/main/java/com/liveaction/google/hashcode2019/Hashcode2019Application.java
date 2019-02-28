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
        for (int i = 0; i < 4; i++) {
            Path path = Paths.get(args[i]);

            Input input = Input.fromFile(path);
            System.out.println(input);

            List<?> results = Lists.newArrayList();
            System.out.println(results);

//            new OutputWriter().writeOutput(results, String.format("output%d", (i+1)));
        }
    }
}
