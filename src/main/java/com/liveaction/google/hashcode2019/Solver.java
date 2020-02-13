package com.liveaction.google.hashcode2019;

import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Output;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

public final class Solver {

    public Output solve(Input input) {
        int total = 0;
        int i = input.pizzaSizes.size();
        IntList pizzasId = new IntArrayList(i);
        while (total < input.maxSlices && i > 0) {
            total += input.pizzaSizes.getInt(i);
            pizzasId.add(i);
            i--;
        }
        if(total > input.maxSlices) {
            pizzasId.remove(pizzasId.size()-1);
        }
        return new Output(input, pizzasId);
    }
}
