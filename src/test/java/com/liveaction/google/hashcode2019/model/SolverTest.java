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
        Input input = Input.fromFile(Paths.get("/home/agjini/workspace/perso/google-hashcode-2019/a_example.txt"));
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
    }
}