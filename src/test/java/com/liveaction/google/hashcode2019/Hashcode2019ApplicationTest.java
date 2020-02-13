package com.liveaction.google.hashcode2019;

import com.liveaction.google.hashcode2019.file.manager.EntryReader;
import com.liveaction.google.hashcode2019.file.manager.OutputWriter;
import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Hashcode2019ApplicationTest {

    private final static String A = "src/test/resources/a_example.in";
    private final static String B = "src/test/resources/b_small.in";
    private final static String C = "src/test/resources/c_medium.in";
    private final static String D = "src/test/resources/d_quite_big.in";
    private final static String E = "src/test/resources/e_also_big.in";

    @Test
    public void test() throws IOException {
        Input input = new EntryReader().getInput(new File(A));
        Output solve = new Solver().solve(input);
        System.out.println("score : "+solve.score());
        new OutputWriter().writeResult(solve);
    }
}
