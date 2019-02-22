package com.liveaction.google.hashcode2019.output.writer;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class OutputWriter {


    public void writeOutput(Collection<?> inputs, String fileName) throws IOException {
        List<String> result = Lists.newArrayList(); // TODO
        Files.write(Paths.get(String.format("%s.out", fileName)), result, Charset.defaultCharset());
    }
}
