package com.liveaction.google.hashcode2019.input;

import com.google.common.collect.ImmutableSet;
import com.liveaction.google.hashcode2019.model.Photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Input {

    private int photoNumber = 0;
    private Set<Photo> photos;


    private Input(int photoNumber, Set<Photo> photos) {
        this.photoNumber = photoNumber;
        this.photos = new HashSet<>(photos);
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public static Input fromFile(Path path) throws IOException {
        // Set of photos to pass to constructor
        final Set<Photo> photos = new HashSet<>();

        List<String> lines = Files.readAllLines(path);
        int inputSize = lines.size();

        String header = lines.get(0);
        int photoNumber = Integer.parseInt(header);

        for (int i = 1; i < inputSize; i++) {
            int index = i - 1;
            String line = lines.get(i);
            String[] lineCells = line.split(" ");
            // get orientation
            final String orientation = lineCells[0];
            boolean horizontal = orientation.equals("H");
            // get number of tags
            final int numberOfTags = Integer.parseInt(lineCells[1]);
            // set of tags
            final ImmutableSet.Builder<String> builder = new ImmutableSet.Builder<String>();
            for (int j = 2; j < lineCells.length; j++) {
                builder.add(lineCells[j]);
            }

            final Photo photo = new Photo(index, builder.build(), horizontal);
            photos.add(photo);
        }

        return new Input(photoNumber, photos);
    }

    public int getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(int photoNumber) {
        this.photoNumber = photoNumber;
    }
}