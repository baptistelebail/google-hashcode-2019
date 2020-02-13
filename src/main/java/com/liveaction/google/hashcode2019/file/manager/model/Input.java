package com.liveaction.google.hashcode2019.file.manager.model;

import it.unimi.dsi.fastutil.ints.IntList;

public final class Input {
    public final int maxSlices;
    public final IntList pizzaSizes;

    public Input(int maxSlices, IntList pizzaSizes) {
        this.maxSlices = maxSlices;
        this.pizzaSizes = pizzaSizes;
    }
}
