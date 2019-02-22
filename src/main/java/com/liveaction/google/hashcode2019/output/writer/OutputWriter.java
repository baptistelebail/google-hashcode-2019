package com.liveaction.google.hashcode2019.output.writer;

import com.liveaction.google.hashcode2019.model.Slice;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OutputWriter {

    public static final String OUTPUT_PATH = "output/output.out";

    public void writeOutput(Collection<Slice> slices) throws IOException {
        List<String> slicesAsString = slices.stream().map(slice -> String.format("%d %d %d %d", slice.r1, slice.c1, slice.r2, slice.c2)).collect(Collectors.toList());
        slicesAsString.add(0, String.valueOf(slices.size()));
        Files.write(Paths.get(URI.create(OUTPUT_PATH)), slicesAsString, Charset.defaultCharset());
    }

}
