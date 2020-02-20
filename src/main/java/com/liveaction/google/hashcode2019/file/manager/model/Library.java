package com.liveaction.google.hashcode2019.file.manager.model;

import com.google.common.base.MoreObjects;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("signupDays", signupDays)
                .add("parellelScanning", parellelScanning)
                .add("books", books)
                .toString();
    }
}
