package com.liveaction.google.hashcode2019.model;

import com.liveaction.google.hashcode2019.input.Input;
import com.liveaction.google.hashcode2019.output.writer.OutputWriter;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SolverTest {

//    @Test
//    public void shouldIndex() {
//        ImmutableList<Photo> tested = ImmutableList.of(new Photo(0, ImmutableSet.of("tag1"), true),
//                new Photo(1, ImmutableSet.of("tag1", "tag2"), true),
//                new Photo(2, ImmutableSet.of("tag2"), true));
//        Collection<IndexedPhoto> indexed = Solver.indexPhoto(tested);
//        Assertions.assertThat(indexed)
//                .extracting(ip -> ip.tags)
//                .containsOnly(ImmutableSet.of(0), ImmutableSet.of(0, 1), ImmutableSet.of(1));
//    }

    @Test
    public void solve() throws IOException {
        Solver solver = new Solver();
        Path path = Paths.get("a_example.txt");
        Input input = Input.fromFile(path);
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
        OutputWriter.writeOutput(solve, path);

        System.out.println(Solver.score(solve));
    }



//    @Test
//    public void solve1() throws IOException {
//        Solver solver = new Solver();
//        Path path = Paths.get("b_lovely_landscapes.txt");
//        Input input = Input.fromFile(path);
//        List<Slide> solve = solver.solve(input.getPhotos());
//        System.out.println(solve);
//        OutputWriter.writeOutput(solve, path);
//    }

//    @Test
//    public void showB() throws IOException {
//        Solver solver = new Solver();
//        Path path = Paths.get("b_lovely_landscapes.txt");
//        Input input = Input.fromFile(path);
//        Set<Photo> photos = input.getPhotos();
//
//        new TagMapping(photos);
//
//        Set<String> tags = Sets.newHashSet();
//        int count = 0;
//        for (Photo photo : photos) {
//            for (String tag : photo.tags) {
//                tags.add(tag);
//                count++;
//            }
//        }
//
//        System.out.println("count "+count);
//        System.out.println("tag size "+tags.size());
//
//    }




    @Test
    public void solve2() throws IOException {
        Solver solver = new Solver();
        Path path = Paths.get("c_memorable_moments.txt");
        Input input = Input.fromFile(path);
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
        System.out.println(Solver.score(solve));
        OutputWriter.writeOutput(solve, path);
    }




    @Test
    public void solve3() throws IOException {
        Solver solver = new Solver();
        Path path = Paths.get("d_pet_pictures.txt");
        Input input = Input.fromFile(path);
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
        System.out.println(Solver.score(solve));
        OutputWriter.writeOutput(solve, path);
    }


    @Test
    public void solve4() throws IOException {
        Solver solver = new Solver();
        Path path = Paths.get("e_shiny_selfies.txt");
        Input input = Input.fromFile(path);
        List<Slide> solve = solver.solve(input.getPhotos());
        System.out.println(solve);
        System.out.println(Solver.score(solve));
        OutputWriter.writeOutput(solve, path);
    }


//    @Test
//    public void shouldMergeVerticals() {
//        int tag1 = 0;
//        int tag2 = 1;
//        int tag3 = 2;
//        int tag4 = 3;
//        int tag5 = 4;
//        IndexedPhoto p1 = new IndexedPhoto(0, ImmutableSet.of(tag1, tag2), false);
//        IndexedPhoto p2 = new IndexedPhoto(1, ImmutableSet.of(tag2, tag3), false);
//        IndexedPhoto p3 = new IndexedPhoto(2, ImmutableSet.of(tag4, tag5), false);
//        IndexedPhoto p4 = new IndexedPhoto(3, ImmutableSet.of(tag1), false);
//        ImmutableList<IndexedPhoto> photos = ImmutableList.of(p1, p2, p3, p4);
//        List<Slide> actual = Solver.mergeVerticalsPhoto(photos);
//        Assertions.assertThat(actual)
//                .containsOnly(new Slide(p1, p3), new Slide(p2, p4));
//    }
}