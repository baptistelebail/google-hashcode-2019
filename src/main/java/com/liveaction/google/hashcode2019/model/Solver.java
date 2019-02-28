package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Solver {

    public static final int LIMIT_COMPARE_PHOTO = 100;

    public List<Slide> solve(Collection<Photo> photos) {
        List<Slide> slides = Lists.newArrayList(flatSlides(photos));
        List<Slide> slideshow = Lists.newArrayList();
        if (slides.isEmpty()) {
            return slideshow;
        }
        Slide slide = slides.get(0);
        slideshow.add(slide);
        slides.remove(slide);
        solveR(slideshow, slides);
        return slideshow;
    }

    private void solveR(List<Slide> slideshow, List<Slide> others) {
        while (!others.isEmpty()) {
            Slide lastSlide = slideshow.get(slideshow.size() - 1);
            Slide optimal = optimal(lastSlide, others, LIMIT_COMPARE_PHOTO);
            slideshow.add(optimal);
            others.remove(optimal);
        }
    }

    private Slide optimal(Slide lastSlide, List<Slide> others, int limit) {
        Slide val = others.stream()
                .limit(limit)
                .map(slide -> Maps.immutableEntry(slide, score(slide, lastSlide)))
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
        return val;
    }

    private List<Slide> flatSlides(Collection<Photo> photos) {
        ImmutableList.Builder<Slide> builder = ImmutableList.builder();
        ImmutableList.Builder<Photo> verticals = ImmutableList.builder();
        for (Photo photo : photos) {
            if (photo.horizontal) {
                builder.add(new Slide(photo));
            } else {
                verticals.add(photo);
            }
        }

        return builder.addAll(mergeVerticalsPhoto(verticals.build())).build();
    }


    static List<Slide> mergeVerticalsPhoto(Collection<Photo> photos) {
        ImmutableList.Builder<Slide> res = ImmutableList.builder();
        List<Photo> remainingPhotos = Lists.newArrayList(photos);
        while (remainingPhotos.size() > 1) {
            Photo firstPhoto = remainingPhotos.remove(0);
            int bestPair = bestPair(firstPhoto, remainingPhotos);
            res.add(new Slide(firstPhoto, remainingPhotos.remove(bestPair)));
        }
        return res.build();
    }

    private static int bestPair(Photo firstPhoto, List<Photo> remainingPhotos) {
        return IntStream.range(0, remainingPhotos.size())
                .limit(LIMIT_COMPARE_PHOTO)
                .mapToObj(photo -> Maps.immutableEntry(photo, Sets.intersection(firstPhoto.tags, remainingPhotos.get(photo).tags).size()))
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();
    }

    int score(Slide s1, Slide s2) {
        Sets.SetView<String> intersection = Sets.intersection(s1.tags(), s2.tags());
        Sets.SetView<String> difference1 = Sets.difference(s1.tags(), s2.tags());
        Sets.SetView<String> difference2 = Sets.difference(s2.tags(), s1.tags());
        return Math.min(intersection.size(), Math.min(difference1.size(), difference2.size()));
    }

    public static Collection<IndexedPhoto> indexPhoto(Collection<Photo> photos) {
        TagMapping tagMapping = new TagMapping(photos);

        return tagMapping.indexedPhotos;
    }

}
