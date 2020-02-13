package com.liveaction.google.hashcode2019.file.manager;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.liveaction.google.hashcode2019.file.manager.model.Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputWriter {

    private final static Path DEST = Paths.get("src/resources/output");

    public Path writeResult(Output output) throws IOException {
        Files.createDirectories(DEST);
        Path dest = DEST.resolve(output.input.name);
        ImmutableList<String> lines = ImmutableList.of("" + output.pizzasIds.size(), Joiner.on(" ").join(output.pizzasIds));
        Files.write(dest, lines);
        return dest;
    }
}
