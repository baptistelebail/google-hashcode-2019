package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Solver {
    private TagMapping tagMapping;

    public static int score(List<Slide> solve) {
        return Flux.fromIterable(solve)
                .buffer(2, 1)
                .filter(l -> l.size() == 2)
                .map(slides -> score(slides.get(0), slides.get(1)))
                .reduce(Integer::sum).block();
    }

    public List<Slide> solve(Collection<Photo> photos, int verticalParam, int optimalParam) {

        tagMapping = new TagMapping(photos);

        List<Slide> slides = Lists.newArrayList(tagMapping.flatSlides);

        List<Slide> slideshow = Lists.newArrayList();
        if (slides.isEmpty()) {
            return slideshow;
        }
        Slide slide = slides.get(0);
        slideshow.add(slide);
        slides.remove(slide);
        solveR(slideshow, slides, optimalParam);
        return slideshow;
    }

    private void solveR(List<Slide> slideshow, List<Slide> others, int optimalParam) {
        while (!others.isEmpty()) {
            Slide lastSlide = slideshow.get(slideshow.size() - 1);
            Slide optimal = optimal2(lastSlide, others, optimalParam);
            slideshow.add(optimal);
            others.remove(optimal);
        }
    }

    private Slide optimal2(Slide lastSlide, List<Slide> others, int optimalParam) {
        for (Integer tag : lastSlide.tags()) {
            
        }
        tagMapping.indexedFlatSlides
    }
    
    private Slide optimal(Slide lastSlide, List<Slide> others, int optimalParam) {
        Slide val = others.stream()
                .limit(optimalParam)
                .map(slide -> Maps.immutableEntry(slide, score(slide, lastSlide)))
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();
        return val;
    }

    static int score(Slide s1, Slide s2) {
        Sets.SetView<Integer> intersection = Sets.intersection(s1.tags(), s2.tags());
        Sets.SetView<Integer> difference1 = Sets.difference(s1.tags(), s2.tags());
        Sets.SetView<Integer> difference2 = Sets.difference(s2.tags(), s1.tags());
        return Math.min(intersection.size(), Math.min(difference1.size(), difference2.size()));
    }

    public static Collection<IndexedPhoto> indexPhoto(Collection<Photo> photos) {
        TagMapping tagMapping = new TagMapping(photos);

        return tagMapping.indexedPhotos;
    }

}
