package com.liveaction.google.hashcode2019.file.manager;

import com.liveaction.google.hashcode2019.file.manager.model.Input;
import com.liveaction.google.hashcode2019.file.manager.model.Library;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.List;

public final class LibraryUtils {

    public List<Integer> getBookFrequency(Input input) {
        int nbBooks = input.books.length;
        IntList frequency = new IntArrayList(nbBooks);
        input.libraries.stream().flatMap(library -> library.books.stream()).map(idBook -> {
            int newFreq = frequency.getInt(idBook)+1;
            frequency.add(idBook.intValue(),newFreq);
        });
        return frequency;
    }
}
