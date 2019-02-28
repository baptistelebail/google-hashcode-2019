package com.liveaction.google.hashcode2019.input;

import org.junit.Test;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputTest {

    @Test
    public void fromFile() throws Exception {
        Path pathToFile = Paths.get(new URI("a_example.txt"));
        final Input input = Input.fromFile(pathToFile);

        System.out.println();
    }
}