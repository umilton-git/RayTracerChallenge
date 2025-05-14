package com.ulysses.raytracer;

import org.junit.Test;
import static org.junit.Assert.*;

public class CanvasTest {

    double delta = 1e-9;
    @Test
    public void testCanvas(){
        Canvas c = new Canvas(10, 20);
        assertEquals(10, c.width);
        assertEquals(20, c.height);
    }

    @Test
    public void testWritePixel(){
        Canvas c = new Canvas(10,20);
        Color red = new Color(1,0,0);

        c.writePixel(2, 3, red);
        assertEquals(red, c.data[2][3]);
    }

    @Test
    public void testCanvasToPPM(){
        Canvas c = new Canvas(5, 3);
        Color red = new Color(1, 0, 0);

        // top‑left pixel
        c.writePixel(0, 0, red);

        // middle of the second row  (row = 1, col = 2)
        c.writePixel(1, 2, red);

        String ppm = c.convertToPPM();
        String[] lines = ppm.split("\n");

        /* ---- header checks ---- */
        assertEquals("P3",   lines[0]);
        assertEquals("5 3",  lines[1]);
        assertEquals("255",  lines[2]);

        /* ---- first pixel row ----
        Expected: 255 0 0  0 0 0  0 0 0  0 0 0  0 0 0  (with trailing space) */
        assertEquals("255 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ", lines[3]);

        /* ---- second pixel row ----
        Red appears at x=2 (third pixel). */
        assertEquals("0 0 0 0 0 0 255 0 0 0 0 0 0 0 0 ",  lines[4]);

        /* ---- 70‑char rule ---- */
        for (int i = 3; i < lines.length; i++) {          // skip header
            assertTrue("PPM line too long: " + lines[i].length(),
                    lines[i].length() <= 70);
        }

        /* ---- ends with newline ---- */
        assertTrue(ppm.endsWith("\n"));
    }
}
