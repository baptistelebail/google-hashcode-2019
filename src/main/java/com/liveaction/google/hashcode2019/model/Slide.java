package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.Sets;

import java.util.Set;

public class Slide {

    public final Photo photo1;
    public final Photo photo2;
    private final Set<String> tags;

    public Slide(Photo photo) {
        this(photo, null);
    }
    public Slide(Photo photo1, Photo photo2) {
        this.photo1 = photo1;
        this.photo2 = photo2;
        if (photo2 == null) {
            this.tags = photo1.tags;
        } else {
            this.tags = Sets.union(photo1.tags, photo2.tags);
        }
    }

    public Set<String> tags() {
        return this.tags;
    }
}
