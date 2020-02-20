package com.liveaction.google.hashcode2019.file.manager.model;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class Output {

    public final Input input;

    public final IntList libraries;

    public final Map<Integer, IntList> booksPerLibrary;

    public Output(Input input, IntList libraries, Map<Integer, IntList> booksPerLibrary) {
        this.input = input;
        this.libraries = libraries;
        this.booksPerLibrary = booksPerLibrary;
    }

    public Output truncate() {
        int nbAvailableDays = input.nbDays;
        Map<Integer, IntList> res = Maps.newHashMap();
        IntList newLibrary = new IntArrayList();
        for (Integer libraryIdx : libraries) {
            IntList books = booksPerLibrary.get(libraryIdx);
            Library library = input.libraries.get(libraryIdx);

            if (library.signupDays >= nbAvailableDays) {
                continue;
            }

            int nbDayForLibrary = (int) Math.ceil((double) books.size() / library.parellelScanning);
            IntList newList;
            if (nbDayForLibrary > nbAvailableDays) {
                newList = books.subList(0, nbAvailableDays * library.parellelScanning);
            } else {
                newList = books;
            }

            res.put(libraryIdx, newList);
            newLibrary.add(libraryIdx);

            nbAvailableDays -= library.signupDays;
        }
        return new Output(input, newLibrary, res);
    }

    //TODO: if a book is registered too late, do not count it
    public long score() {
        return booksPerLibrary.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()).stream()
                .map(bookId -> input.books[bookId])
                .reduce((i, j) -> i + j)
                .map(Integer::longValue)
                .orElse(0L);
    }


}
