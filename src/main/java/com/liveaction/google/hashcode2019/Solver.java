package com.liveaction.google.hashcode2019;

import com.google.common.collect.Maps;
import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Library;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private Optional<Library> bestLibrary(List<Library> libraries, int daysRemaining, int[] books) {
        return libraries.stream().max(Comparator.comparingInt(l -> scoreLibrary(l, daysRemaining, books)));
    }

    private int scoreLibrary(Library library, int daysRemaining, int[] books) {
        int realDays = daysRemaining - library.signupDays;
        int nbBooks = realDays * library.parellelScanning;
        List<Integer> orderedBooks = library.books
                .stream()
                .sorted(Comparator.comparingInt(b -> -books[b]))
                .collect(Collectors.toList());
        return orderedBooks.subList(0, nbBooks)
                .stream()
                .reduce((i, j) -> i+j)
                .orElse(0);

    }
}
