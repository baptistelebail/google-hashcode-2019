package com.liveaction.google.hashcode2019.file.manager;

import com.liveaction.google.hashcode2019.file.manager.model.Input;
import it.unimi.dsi.fastutil.ints.IntArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EntryReader {

    public Input getInput(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int maxSlices = scanner.nextInt();
        int nbPizzas = scanner.nextInt();
        IntArrayList pizzaSizes = new IntArrayList(nbPizzas);
        for(int i = 0; i < nbPizzas; i++) {
            pizzaSizes.add(scanner.nextInt());
        }
        return new Input(maxSlices, pizzaSizes, file.getName());
    }
}
