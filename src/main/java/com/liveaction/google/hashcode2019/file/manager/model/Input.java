package com.liveaction.google.hashcode2019.file.manager.model;

import it.unimi.dsi.fastutil.ints.IntList;

public final class Input {
    public final int maxSlices;
    public final IntList pizzaSizes;
    public final String name;

    public Input(int maxSlices, IntList pizzaSizes, String name) {
        this.maxSlices = maxSlices;
        this.pizzaSizes = pizzaSizes;
        this.name = name;
    }
}
