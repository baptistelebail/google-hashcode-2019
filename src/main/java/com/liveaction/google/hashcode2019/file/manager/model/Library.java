package com.liveaction.google.hashcode2019.file.manager.model;

import it.unimi.dsi.fastutil.ints.IntList;

public final class Library {
    public final int signupDays;
    public final int parellelScanning;
    public final IntList books;

    public Library(int signupDays, int parellelScanning, IntList books) {
        this.signupDays = signupDays;
        this.parellelScanning = parellelScanning;
        this.books = books;
    }
}
