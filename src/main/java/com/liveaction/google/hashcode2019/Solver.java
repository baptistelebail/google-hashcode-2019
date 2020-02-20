package com.liveaction.google.hashcode2019;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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

        List<Library> libsToParse = Lists.newArrayList(input.libraries);
        int days = input.nbDays;

        while(libsToParse.size() > 0) {
            List<Library> libsToRemove = Lists.newArrayList();
            for(Library lib : libsToParse) {
                if (lib.signupDays <= days) {
                    libsToRemove.add(lib);
                }
            }
            libsToParse.removeAll(libsToRemove);
            Library bestLib = bestLibrary(libsToParse, days, input.books).orElse(null);
            if(bestLib == null)            {
                System.out.println("ERROR : bestLib should not be null");
                break;
            }
            days = days - bestLib.signupDays;
            libsToParse.remove(bestLib);
            libraries.add(input.libraries.indexOf(bestLib));
            List<Integer> collect = bestLib.books.stream()
                    .sorted(Comparator.comparingInt(b -> -input.books[b]))
                    .collect(Collectors.toList());
            booksPerLibrary.put(input.libraries.indexOf(bestLib), new IntArrayList(collect));
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
        List<Integer> booksToScan;
        booksToScan = nbBooks > orderedBooks.size() ? orderedBooks : orderedBooks.subList(0, nbBooks);
        Integer totalScore = booksToScan
                .stream()
                .reduce((i, j) -> i + j)
                .orElse(0);

        return totalScore / library.signupDays;
    }


}
