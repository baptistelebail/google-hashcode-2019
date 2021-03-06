package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.*;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.*;
import java.util.stream.IntStream;

public final class TagMapping {

    public static final int LIMIT_COMPARE_PHOTO = 1000;

    public final Object2IntMap<String> tagByIndex;
    public SortedSet<Slide>[] slidesByTagIndex;
    public final List<Slide> flatSlides;
    public Set<IndexedPhoto> indexedPhotos;


    public TagMapping(Collection<Photo> photos) {
        tagByIndex = new Object2IntOpenHashMap<>();
        indexedPhotos = new HashSet<>();

        int val = 0;

        for (Photo photo : photos) {
            for (String tag : photo.tags) {
                if (!tagByIndex.containsKey(tag)) {
                    tagByIndex.put(tag, val++);
                }
            }
        }


        System.out.println("Tags indexed !");

        for (Photo photo : photos) {
            IntOpenHashSet sets = new IntOpenHashSet(photo.tags.size());
            photo.tags.forEach(tag -> sets.add(tagByIndex.getInt(tag)));
            IndexedPhoto indexedPhoto = new IndexedPhoto(photo.index, sets, photo.horizontal);

            indexedPhotos.add(indexedPhoto);
        }

        flatSlides = flatSlides(indexedPhotos);


        Set<Slide>[] tmp = new Set[val];

        for (int i = 0; i < tagByIndex.size(); i++) {
            tmp[i] = new HashSet<>();
        }

        for (Slide slide : flatSlides) {
            for (int tag : slide.tags()) {
                tmp[tag].add(slide);
            }
        }

        slidesByTagIndex = new SortedSet[val];

        for (int index : tagByIndex.values()) {
            slidesByTagIndex[index] = new TreeSet<>(tmp[index]);
        }

    }


    private List<Slide> flatSlides(Collection<IndexedPhoto> photos) {
        ImmutableList.Builder<Slide> builder = ImmutableList.builder();
        ImmutableList.Builder<IndexedPhoto> verticals = ImmutableList.builder();
        for (IndexedPhoto photo : photos) {
            if (photo.horizontal) {
                builder.add(new Slide(photo));
            } else {
                verticals.add(photo);
            }
        }

        return builder.addAll(mergeVerticalsPhoto(verticals.build())).build();
    }


    static List<Slide> mergeVerticalsPhoto(Collection<IndexedPhoto> photos) {
        ImmutableList.Builder<Slide> res = ImmutableList.builder();
        List<IndexedPhoto> remainingPhotos = Lists.newArrayList(photos);
        while (remainingPhotos.size() > 1) {
            IndexedPhoto firstPhoto = remainingPhotos.remove(0);
            int bestPair = bestPair(firstPhoto, remainingPhotos);
            res.add(new Slide(firstPhoto, remainingPhotos.remove(bestPair)));
        }
        return res.build();
    }

    private static int bestPair(IndexedPhoto firstPhoto, List<IndexedPhoto> remainingPhotos) {
        return IntStream.range(0, remainingPhotos.size())
                .limit(LIMIT_COMPARE_PHOTO)
                .mapToObj(photo -> Maps.immutableEntry(photo, Sets.intersection(firstPhoto.tags, remainingPhotos.get(photo).tags).size()))
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();
    }


}
