package com.liveaction.google.hashcode2019.file.manager;

import com.liveaction.google.hashcode2019.file.manager.model.Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputWriter {

    private final static Path DEST = Paths.get("src/resources/output");

    public Path writeResultInFile(Output output) throws IOException {
        Files.createDirectories(DEST);
        Path dest = DEST.resolve(output.input.name);

        writeResult(output, dest);
        return dest;
    }

    private void writeResult(Output output, Path dest) throws IOException {
    }
}
