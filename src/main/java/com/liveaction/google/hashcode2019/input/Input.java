package com.liveaction.google.hashcode2019.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Input {

    public Input() {
    }

    public static Input fromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        int inputSize = lines.size();

        String header = lines.get(0);
        String[] headerCells = header.split(" ");

        for (int i = 1; i < inputSize; i++) {
            String line = lines.get(i);

            for (int j = 0; j < line.length(); j++) {
            }
        }

        return new Input();
    }
}