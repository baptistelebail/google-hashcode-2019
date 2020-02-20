package com.liveaction.google.hashcode2019;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Library;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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

        while (libsToParse.size() > 0) {
            Tuple2<Library, List<Integer>> libAndBooks = bestLibrary(libsToParse, days, input.books).orElse(null);
            if (libAndBooks == null) {
                System.out.println("ERROR : bestLib should not be null");
                break;
            }
            Library bestLib = libAndBooks.getT1();

            days = days - bestLib.signupDays;
            libsToParse.remove(bestLib);
            libraries.add(input.libraries.indexOf(bestLib));
            booksPerLibrary.put(input.libraries.indexOf(bestLib), new IntArrayList(libAndBooks.getT2()));
            int finalDays = days;
            libsToParse.removeIf(library -> library.signupDays >= finalDays);
            libsToParse.forEach(l -> l.books.removeAll(libAndBooks.getT2()));
        }
        return new Output(input, libraries, booksPerLibrary)
                .truncate();
    }

    private Optional<Tuple2<Library, List<Integer>>> bestLibrary(List<Library> libraries, int daysRemaining, int[] books) {
        return libraries.stream()
                .map(library -> Tuples.of(scoreLibrary(library, daysRemaining, books), library))
                .max(Comparator.comparingInt(t -> t.getT1().getT1()))
                .map(t -> Tuples.of(t.getT2(), t.getT1().getT2()));
    }

    private Tuple2<Integer, List<Integer>> scoreLibrary(Library library, int daysRemaining, int[] books) {
        int realDays = daysRemaining - library.signupDays;
        List<Integer> orderedBooks = library.books
                .stream()
                .sorted(Comparator.comparingInt(b -> -books[b]))
                .collect(Collectors.toList());
        List<Integer> booksToScan;
        booksToScan = realDays > (orderedBooks.size()-1)/library.parellelScanning ? orderedBooks : orderedBooks.subList(0, realDays*library.parellelScanning);
        Integer totalScore = booksToScan
                .stream()
                .reduce((i, j) -> i + j)
                .orElse(0);

        return Tuples.of(totalScore / library.signupDays, booksToScan);
    }
}
