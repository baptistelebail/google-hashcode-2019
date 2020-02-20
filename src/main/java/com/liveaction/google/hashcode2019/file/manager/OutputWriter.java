package com.liveaction.google.hashcode2019.file.manager;

import com.google.common.base.Joiner;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class OutputWriter {

    private final static Path DEST = Paths.get("src/resources/output");

    public Path writeResultInFile(Output output) throws IOException {
        Path dest = DEST.resolve(output.input.name);
        try {
            Files.delete(dest);
        } catch (IOException e) {

        }
        Files.createDirectories(DEST);
        writeResult(output, dest);
        return dest;
    }

    private void writeResult(Output output, Path dest) throws IOException {
        Files.write(dest, (Integer.toString(output.booksPerLibrary.size())+"\n").getBytes());
        for (Integer library : output.libraries) {
            IntList books = output.booksPerLibrary.get(library);
            String format = String.format("%d %d\n", library, books.size());
            Files.write(dest, format.getBytes(), StandardOpenOption.APPEND);
            String join = Joiner.on(" ").join(books)+"\n";
            Files.write(dest, join.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
