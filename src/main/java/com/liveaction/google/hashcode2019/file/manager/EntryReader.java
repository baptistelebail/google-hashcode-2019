package com.liveaction.google.hashcode2019.file.manager;

import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Library;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntryReader {

    public Input getInput(File file) throws FileNotFoundException {
        // create libraries first
        List<Library> libraries = new ArrayList<>();

        String name = file.getName();
        Scanner scanner = new Scanner(file);
        // Parse first line nbBooks & nbLibrary
        int nbBooks = scanner.nextInt();
        int nbLibraries = scanner.nextInt();
        int nbDays = scanner.nextInt();
        // Next line: get score of different books
        scanner.nextLine();
        int[] bookScore = new int[nbBooks];
        for(int i=0; i<nbBooks; i++) {
            bookScore[i]= scanner.nextInt();
        }
        // Parse libraries informations
        for(int i=0; i<nbLibraries; i++) {
            // First line:
            int numberOfBooks = scanner.nextInt();
            int numberOfDaysForSignup = scanner.nextInt();
            int shippingPerDay = scanner.nextInt();
            // Second line
            scanner.nextLine();
            String bookIdsLine = scanner.nextLine();
            System.out.println(bookIdsLine);
            IntList bookIds = new IntArrayList();
            String[] strBookIds = bookIdsLine.split(" ");
            for (int j = 0; j < strBookIds.length; j++) {
                bookIds.add(Integer.parseInt(strBookIds[j]));
            }

            Library library = new Library(numberOfDaysForSignup, shippingPerDay, bookIds);
            libraries.add(library);
        }

        Input input = new Input(name, bookScore, libraries, nbDays);
        return input;
    }
}
