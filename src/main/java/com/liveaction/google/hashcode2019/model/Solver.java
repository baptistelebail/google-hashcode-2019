package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.List;

import java.util.Collection;

public class Solver {

    public List<Slide> solve(List<Photo> photos) {
        List<Slide>  slides = flatSlides(photos);
        if (photos.isEmpty())  {
            return ImmmutableList();
        }
    }

    private List<Slide> flatSlides(List<Photo> photos) {
        return null;
    }

    int score(Slide s1, Slide s2) {
        Sets.SetView<String> intersection = Sets.intersection(s1.tags(), s2.tags());
        Sets.SetView<String> difference1 = Sets.difference(s1.tags(), s2.tags());
        Sets.SetView<String> difference2 = Sets.difference(s2.tags(), s1.tags());
        return Math.min(intersection.size(), Math.min(difference1.size(), difference2.size()));
    }

    public static Collection<IndexedPhoto> indexPhoto(Collection<Photo> photos) {
//        Object2IntMap<String> index = new Object2IntOpenHashMap<>();
//        for (Photo photo : photos) {
//
//        }
//        index.containsKey()
        return ImmutableList.of();
    }

}
