package com.liveaction.google.hashcode2019.file.manager;

import com.google.common.base.Joiner;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntList;

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
        Files.write(dest, Integer.toString(output.booksPerLibrary.size()).getBytes());
        for (Integer library : output.libraries) {
            IntList books = output.booksPerLibrary.get(library);
            Files.write(dest, String.format("%d %d", library, books.size()).getBytes());
            String join = Joiner.on(" ").join(books);
            Files.write(dest, join.getBytes());
        }
    }
}
