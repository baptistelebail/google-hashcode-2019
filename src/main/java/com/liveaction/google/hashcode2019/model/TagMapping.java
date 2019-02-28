package com.liveaction.google.hashcode2019.model;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class TagMapping {

    public final Object2IntMap<String> tagByIndex;
    public final Int2ObjectMap<Set<IndexedPhoto>> photosByTagIndex;
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

        photosByTagIndex = new Int2ObjectArrayMap<>(tagByIndex.size());

        System.out.println("LA1 !");

        for (int index : tagByIndex.values()) {
            photosByTagIndex.put(index, new HashSet<>());
        }

        System.out.println("LA2 !");

        for (Photo photo : photos) {
            IntOpenHashSet sets = new IntOpenHashSet(photo.tags.size());
            photo.tags.forEach(tag -> sets.add(tagByIndex.getInt(tag)));
            IndexedPhoto indexedPhoto = new IndexedPhoto(photo.index, sets, photo.horizontal);

            indexedPhotos.add(indexedPhoto);

            for (String tag : photo.tags) {
                photosByTagIndex.get(tagByIndex.getInt(tag)).add(indexedPhoto);
            }
        }

        System.out.println("LA3 !");
    }
}
