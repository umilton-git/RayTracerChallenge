package com.ulysses.raytracer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello and welcome!");

        Canvas canvas = new Canvas(5, 3);
        Color red = new Color(1, 0, 0);
        canvas.writePixel(2, 1, red);
        Files.writeString(
                Path.of("img.ppm"),
                canvas.convertToPPM(),
                StandardCharsets.US_ASCII);
    }
}