package com.liveaction.google.hashcode2019.file.manager;

import com.google.common.base.Joiner;
import com.liveaction.google.hashcode2019.file.manager.model.Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputWriter {

    private final static Path DEST = Paths.get("/tmp/hashcode");

    public void writeResult(Output output) throws IOException {
        Files.createDirectories(DEST);
        Path dest = DEST.resolve(output.input.name);
        Files.write(dest, ("" + output.pizzasIds.size()).getBytes());
        Files.write(dest, Joiner.on(" ").join(output.pizzasIds).getBytes());
    }
}
