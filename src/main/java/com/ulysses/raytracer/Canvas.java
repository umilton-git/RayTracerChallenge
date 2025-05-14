package com.ulysses.raytracer;

import java.util.Arrays;

public class Canvas {
    int width, height;
    Color[][] data;
    private static final int MAX_COLOR_VALUE = 255;

    public Canvas(int w, int h) {
        this.width = w;
        this.height = h;
        this.data = new Color[height][width];
        Color black = new Color(0, 0, 0);
        for (Color[] row : data) Arrays.fill(row, black);
    }

    public void writePixel(int row, int col, Color c) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            data[row][col] = c;
        }
        else {
            throw new IllegalArgumentException(
                    "Pixel (" + row + "," + col + ") out of bounds; canvas is " +
                            width + "×" + height);
        }
    }

    /** ASCII (P3) export.  Returns the entire file as a String. */
    public String convertToPPM() {
        StringBuilder sb = new StringBuilder();

        /* 1.  Header */
        sb.append("P3\n")
                .append(width).append(' ').append(height).append('\n')
                .append(MAX_COLOR_VALUE).append('\n');

        /* 2.  Body – row major, top to bottom */
        int lineChars = 0;                         // current output line length
        for (int y = 0; y < height; y++) {         // NB: y = row
            for (int x = 0; x < width; x++) {      //     x = column
                Color c = data[y][x];
                int r = scaled(c.r());
                int g = scaled(c.g());
                int b = scaled(c.b());

                /* write three ints, keeping each output line ≤ 70 chars */
                lineChars = appendInt(sb, r, lineChars);
                lineChars = appendInt(sb, g, lineChars);
                lineChars = appendInt(sb, b, lineChars);
            }
            sb.append('\n');
            lineChars = 0;
        }
        return sb.toString();
    }

    /* ---------- helper methods ----------- */

    /** Clamp colour component ∈ [0,1] and scale to 0 – 255 integer. */
    private static int scaled(double c) {
        c = Math.min(1.0, Math.max(0.0, c));       // clamp
        return (int) Math.round(c * MAX_COLOR_VALUE);
    }

    /** Append an int followed by a space, inserting a newline if it would
     *  push the current line beyond 70 characters.   Returns new line length. */
    private static int appendInt(StringBuilder sb, int value, int lineChars) {
        String s = Integer.toString(value);
        if (lineChars + s.length() + 1 > 70) {     // +1 for trailing space
            sb.append('\n');
            lineChars = 0;
        }
        sb.append(s).append(' ');
        return lineChars + s.length() + 1;
    }
}
