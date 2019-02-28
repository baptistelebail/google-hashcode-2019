package com.liveaction.google.hashcode2019.model;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.HashSet;
import java.util.Set;

public final class TagMapping {

    private final Int2ObjectMap<Set<IndexedPhoto>> mapping;
    private final Set<IndexedPhoto> photos;

    public TagMapping(Set<IndexedPhoto> photos) {
        this.photos = photos;
        this.mapping = new Int2ObjectArrayMap<>();
        map();
    }

    public Set<IndexedPhoto> forTag(int tag) {
        return mapping.get(tag);
    }

    private void map() {
        for (IndexedPhoto photo : photos) {
            for (int tag : photo.tags) {
                mapping.computeIfAbsent(tag, ignored -> new HashSet<>());
                mapping.get(tag).add(photo);
            }
        }
    }
}
