package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
            Slide optimal = optimal(lastSlide, others);
            slideshow.add(optimal);
            others.remove(optimal);
            solveR(slideshow, others);
        }
    }

    private Slide optimal(Slide lastSlide, List<Slide> others) {
        return others.stream()
                .map(slide -> Maps.immutableEntry(slide, score(slide, lastSlide)))
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
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
        int val = 0;
        Object2IntMap<String> index = new Object2IntOpenHashMap<>();
        for (Photo photo : photos) {
            for (String tag : photo.tags) {
                if (!index.containsKey(tag)) {
                    index.put(tag, val++);
                }
            }
        }
        return photos.stream()
                .map(photo -> {
                    IntOpenHashSet sets = new IntOpenHashSet(photo.tags.size());
                    photo.tags.forEach(s -> sets.add(index.getInt(s)));
                    return new IndexedPhoto(photo.index, sets, photo.horizontal);
                }).collect(ImmutableList.toImmutableList());
    }

}
