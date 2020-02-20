package com.liveaction.google.hashcode2019.file.manager.model;

import it.unimi.dsi.fastutil.ints.IntList;

import java.util.Collection;
import java.util.Map;

public final class Output {

    public final Input input;

    public final IntList libraries;

    public final Map<Integer, IntList> booksPerLibrary;

    public Output(Input input, IntList libraries, Map<Integer, IntList> booksPerLibrary) {
        this.input = input;
        this.libraries = libraries;
        this.booksPerLibrary = booksPerLibrary;
    }

    //TODO: if a book is registered too late, do not count it
    public long score() {
        return booksPerLibrary.values().stream()
                .flatMap(Collection::stream)
                .map(bookId -> input.books[bookId])
                .reduce((i, j) -> i+j)
                .map(Integer::longValue)
                .orElse(0L);
    }



}
