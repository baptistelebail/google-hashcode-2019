package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.liveaction.google.hashcode2019.input.Input;
import com.liveaction.google.hashcode2019.output.writer.OutputWriter;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

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
        Path path = Paths.get("e_shiny_selfies.txt");
        Input input = Input.fromFile(path);
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
        OutputWriter.writeOutput(solve, path);

        System.out.println(Solver.score(solve));
    }

    @Test
    public void shouldMergeVerticals() {
        int tag1 = 0;
        int tag2 = 1;
        int tag3 = 2;
        int tag4 = 3;
        int tag5 = 4;
        IndexedPhoto p1 = new IndexedPhoto(0, ImmutableSet.of(tag1, tag2), false);
        IndexedPhoto p2 = new IndexedPhoto(1, ImmutableSet.of(tag2, tag3), false);
        IndexedPhoto p3 = new IndexedPhoto(2, ImmutableSet.of(tag4, tag5), false);
        IndexedPhoto p4 = new IndexedPhoto(3, ImmutableSet.of(tag1), false);
        ImmutableList<IndexedPhoto> photos = ImmutableList.of(p1, p2, p3, p4);
        List<Slide> actual = Solver.mergeVerticalsPhoto(photos);
        Assertions.assertThat(actual)
                .containsOnly(new Slide(p1, p3), new Slide(p2, p4));
    }
}