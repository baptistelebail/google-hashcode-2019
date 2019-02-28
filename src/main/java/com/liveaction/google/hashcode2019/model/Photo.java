package com.liveaction.google.hashcode2019.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

public class Photo {

    public final int index;

    public final ImmutableSet<String> tags;

    public final boolean horizontal;

    public Photo(int index, ImmutableSet<String> tags, boolean horizontal) {
        this.index = index;
        this.tags = tags;
        this.horizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return index == photo.index &&
                horizontal == photo.horizontal &&
                Objects.equals(tags, photo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, tags, horizontal);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("index", index)
                .add("tags", tags)
                .add("horizontal", horizontal)
                .toString();
    }
}
