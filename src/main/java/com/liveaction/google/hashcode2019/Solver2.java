package com.liveaction.google.hashcode2019;

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

public final class Solver2 {

    public Output solve(Input input) {
        IntList libraries = new IntArrayList(input.libraries.size());
        Map<Integer, IntList> booksPerLibrary = Maps.newHashMap();

        List<Library> libsToParse = Lists.newArrayList(input.libraries);
        int days = input.nbDays;

        while (libsToParse.size() > 0) {
            Library bestLib = bestLibrary(libsToParse, days, input.books).orElse(null);
            if (bestLib == null) {
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
         //   System.out.println(bestLib);
            int daysFinal = days;
            libsToParse.removeIf(lib -> lib.signupDays >= daysFinal);
            libsToParse.forEach(l -> l.books.removeAll(bestLib.books));
        }
        return new Output(input, libraries, booksPerLibrary)
                .truncate();
    }

    private Optional<Library> bestLibrary(List<Library> libraries, int daysRemaining, int[] books) {
        return libraries.stream()
                .min(Comparator.comparing(library -> library.signupDays));
    }
}
