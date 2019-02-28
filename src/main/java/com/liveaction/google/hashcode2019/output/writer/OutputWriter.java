package com.liveaction.google.hashcode2019.output.writer;

import com.google.common.collect.ImmutableList;
import com.liveaction.google.hashcode2019.model.Slide;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class OutputWriter {

    public static void writeOutput(Collection<Slide> inputs, Path input) throws IOException {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        builder.add("" + inputs.size());
        for (Slide slide : inputs) {
            if (slide.photo2 != null) {
                builder.add(slide.photo1.index + " " + slide.photo2.index);
            } else {
                builder.add("" + slide.photo1.index);
            }
        }
        Files.write(Paths.get(String.format("%s.out", input)), builder.build(), Charset.defaultCharset());
    }
}
