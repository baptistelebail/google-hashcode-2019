package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;

public class Solver {

    public List<Slide> solve(List<Photo> photos) {
        List<Slide> slides = flatSlides(photos);
        List<Slide> list = Lists.newArrayList();
        solveR(slides, list);
        return list;
    }

    private void solveR(List<Slide> slideshow, List<Slide> others) {
        if (!others.isEmpty()) {
            Slide lastSlide = slideshow.get(slideshow.size() - 1);
            int optIndex = optimal(lastSlide, others);
            Slide optimal = others.get(optIndex);
            slideshow.add(optimal);
            others.remove(optIndex);
            solveR(slideshow, others);
        }
    }

    private int optimal(Slide lastSlide, List<Slide> others) {
        return 0;
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
