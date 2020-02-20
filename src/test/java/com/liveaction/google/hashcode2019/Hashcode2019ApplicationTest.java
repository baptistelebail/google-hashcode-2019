package com.liveaction.google.hashcode2019;

import com.google.common.collect.ImmutableList;
import com.liveaction.google.hashcode2019.file.manager.EntryReader;
import com.liveaction.google.hashcode2019.file.manager.OutputWriter;
import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Hashcode2019ApplicationTest {

    private final static String A = "src/test/resources/a_example.txt";
    private final static String B = "src/test/resources/b_read_on.txt";
    private final static String C = "src/test/resources/c_incunabula.txt";
    private final static String D = "src/test/resources/d_tough_choices.txt";
    private final static String E = "src/test/resources/e_so_many_books.txt";
    private final static String F = "src/test/resources/f_libraries_of_the_world.txt";

    @Test
    public void test() throws IOException {
        for (String file : ImmutableList.of(A, B, C, D, E ,F)) {
            Input input = new EntryReader().getInput(new File(file));
            Output solve = new Solver().solve(input);
            System.out.println("score : " + solve.score());
            Path dest = new OutputWriter().writeResultInFile(solve);
            System.out.println("dest : " + dest);
        }
    }
}
