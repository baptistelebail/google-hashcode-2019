package com.liveaction.google.hashcode2019;

import com.google.common.collect.Maps;
import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.Map;

public final class Solver {

    public Output solve(Input input) {

        IntList libraries = new IntArrayList(input.libraries.size());
        Map<Integer, IntList> booksPerLibrary = Maps.newHashMap();


        for(int i = 0; i < input.libraries.size(); i++) {
            libraries.add(i);
            booksPerLibrary.put(i, input.libraries.get(i).books);
        }

        return new Output(input, libraries, booksPerLibrary);
    }
}
