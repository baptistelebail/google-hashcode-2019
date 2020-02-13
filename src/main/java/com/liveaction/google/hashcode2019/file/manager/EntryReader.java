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
        String[] sizes = scanner.nextLine().split(" ");
        IntArrayList pizzaSizes = new IntArrayList(nbPizzas);
        for (String s : sizes) {
            pizzaSizes.add(Integer.parseInt(s));
        }
        return new Input(maxSlices, pizzaSizes);
    }
}
