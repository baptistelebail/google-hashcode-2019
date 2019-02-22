package com.liveaction.google.hashcode2019.output.writer;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class OutputWriter {

    public static final String OUTPUT_PATH = "output.out";

    public void writeOutput(Collection<?> inputs) throws IOException {
        List<String> result = Lists.newArrayList(); // TODO
        Files.write(Paths.get(OUTPUT_PATH), result, Charset.defaultCharset());
    }
}
