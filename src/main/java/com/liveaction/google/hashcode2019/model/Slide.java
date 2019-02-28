package com.liveaction.google.hashcode2019.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slide slide = (Slide) o;
        return Objects.equals(photo1, slide.photo1) &&
                Objects.equals(photo2, slide.photo2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photo1, photo2);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("photo1", photo1)
                .add("photo2", photo2)
                .toString();
    }
}
