package com.liveaction.google.hashcode2019;

import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

public final class Solver {

    public Output solve(Input input) {
        int total = 0;
        IntList pizzasId = new IntArrayList(input.pizzaSizes.size() - 1);
        for (int i = input.pizzaSizes.size() - 1; i >= 0; i--) {
            if (total + input.pizzaSizes.getInt(i) <= input.maxSlices) {
                total += input.pizzaSizes.getInt(i);
                pizzasId.add(i);
            }
        }
        return new Output(input, pizzasId);
    }
}
