package com.liveaction.google.hashcode2019.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Set;

public final class IndexedPhoto implements Comparable<IndexedPhoto> {
    public final int index;

    public final Set<Integer> tags;

    public final boolean horizontal;

    public IndexedPhoto(int index, Set<Integer> tags, boolean horizontal) {
        this.index = index;
        this.tags = tags;
        this.horizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexedPhoto that = (IndexedPhoto) o;
        return index == that.index &&
                horizontal == that.horizontal &&
                Objects.equals(tags, that.tags);
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

    @Override
    public int compareTo(IndexedPhoto o) {
        return Integer.compare(tags.size(), o.tags.size());
    }
}
