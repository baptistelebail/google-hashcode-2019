package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void shouldIndex() {
        ImmutableList<Photo> tested = ImmutableList.of(new Photo(0, ImmutableSet.of("tag1"), true),
                new Photo(1, ImmutableSet.of("tag1", "tag2"), true),
                new Photo(2, ImmutableSet.of("tag2"), true));
        Collection<IndexedPhoto> indexed = Solver.indexPhoto(tested);
        Assertions.assertThat(indexed)
                .extracting(ip -> ip.tags)
                .containsOnly(ImmutableSet.of(0), ImmutableSet.of(0, 1), ImmutableSet.of(1));
    }
}