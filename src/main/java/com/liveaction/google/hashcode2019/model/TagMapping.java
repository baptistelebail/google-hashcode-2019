package com.liveaction.google.hashcode2019.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class TagMapping {

    private final Map<String, Set<Photo>> mapping;
    private final Set<Photo> photos;

    public TagMapping(Set<Photo> photos) {
        this.photos = photos;
        this.mapping = new HashMap<>();

        map();
    }

    public Set<Photo> forTag(String tag) {
        return mapping.get(tag);
    }

    private void map() {
        for (Photo photo : photos) {
            for (String tag : photo.tags) {
                mapping.computeIfAbsent(tag, ignored -> new HashSet<>());
                mapping.get(tag).add(photo);
            }
        }
    }
}
