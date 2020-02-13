package com.liveaction.google.hashcode2019.file.manager.model;

import it.unimi.dsi.fastutil.ints.IntList;

public final class Output {

    public final Input input;
    public final IntList pizzasIds;

    public Output(Input input, IntList pizzasIds) {
        this.input = input;
        this.pizzasIds = pizzasIds;
    }

    public long score() {
        return pizzasIds.stream()
                .map(pizzasIds::getInt)
                .mapToLong(Long::valueOf)
                .sum();
    }
}
