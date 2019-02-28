package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableSortedSet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class TagMapping {

    public final Object2IntMap<String> tagByIndex;
    public final Set<IndexedPhoto>[] photosByTagIndex;
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

        Set<IndexedPhoto>[] tmp = new Set[val];
        for (int index : tagByIndex.values()) {
            tmp[index] = new HashSet<>();
        }

        System.out.println("Mapping initialized");

        for (Photo photo : photos) {
            IntOpenHashSet sets = new IntOpenHashSet(photo.tags.size());
            photo.tags.forEach(tag -> sets.add(tagByIndex.getInt(tag)));
            IndexedPhoto indexedPhoto = new IndexedPhoto(photo.index, sets, photo.horizontal);

            indexedPhotos.add(indexedPhoto);

            for (String tag : photo.tags) {
                tmp[tagByIndex.getInt(tag)].add(indexedPhoto);
            }
        }

        photosByTagIndex = new Set[val];
        for (int index : tagByIndex.values()) {
            photosByTagIndex[index] = ImmutableSortedSet.copyOf(tmp[index]);
        }

        System.out.println("Mapping done");
    }
}
