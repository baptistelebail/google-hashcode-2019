package com.liveaction.google.hashcode2019;

import org.junit.Test;

public class Hashcode2019ApplicationTest {

    @Test
    public void shouldLoadPizzaInput() throws Exception {
        Hashcode2019Application hashcode2019Application = new Hashcode2019Application();

        String args[] = new String[]{"src/test/resources/input1.in", "src/test/resources/input2.in", "src/test/resources/input3.in", "src/test/resources/input4.in"};

        hashcode2019Application.main(args);
    }
}
