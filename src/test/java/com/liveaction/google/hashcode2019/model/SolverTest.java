package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.liveaction.google.hashcode2019.input.Input;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

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

    @Test
    public void solve() throws IOException {
        Solver solver = new Solver();
        Input input = Input.fromFile(Paths.get("/home/simon/workspace/github/google-hashcode-2019/e_shiny_selfies.txt"));
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
    }

    @Test
    public void shouldMergeVerticals() {
        String tag1 = "tag1";
        String tag2 = "tag2";
        String tag3 = "tag3";
        String tag4 = "tag4";
        String tag5 = "tag5";
        Photo p1 = new Photo(0, ImmutableSet.of(tag1, tag2), false);
        Photo p2 = new Photo(1, ImmutableSet.of(tag2, tag3), false);
        Photo p3 = new Photo(2, ImmutableSet.of(tag4, tag5), false);
        Photo p4 = new Photo(3, ImmutableSet.of(tag1), false);
        ImmutableList<Photo> photos = ImmutableList.of(p1, p2, p3, p4);
        List<Slide> actual = Solver.mergeVerticalsPhoto(photos);
        Assertions.assertThat(actual)
                .containsOnly(new Slide(p1, p3), new Slide(p2, p4));
    }
}