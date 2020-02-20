package com.liveaction.google.hashcode2019.file.manager.model;

import java.util.List;

public final class Input {
    public final String name;
    public final int[] books;
    public final List<Library> libraries;
    public final int nbDays;

    public Input(String name, int[] books, List<Library> libraries, int nbDays) {
        this.name = name;
        this.books = books;
        this.libraries = libraries;
        this.nbDays = nbDays;
    }
}
