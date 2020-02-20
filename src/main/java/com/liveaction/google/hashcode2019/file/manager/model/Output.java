package com.liveaction.google.hashcode2019.file.manager.model;

import it.unimi.dsi.fastutil.ints.IntList;

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

    public long score() {
        return 0;
    }



}
