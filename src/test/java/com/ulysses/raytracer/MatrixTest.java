package com.ulysses.raytracer;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {
    double delta = 1e-9;

    @Test
    public void testMatrix(){
        Matrix m = new Matrix(4, 4, 1, 2, 3, 4, 5.5, 6.5, 7.5, 8.5, 9, 10, 11, 12, 13.5, 14.5, 15.5, 16.5);

        assertEquals(1, m.at(0, 0), delta);
        assertEquals(5.5, m.at(1, 0), delta);
        assertEquals(7.5, m.at(1, 2), delta);
        assertEquals(11, m.at(2, 2), delta);
        assertEquals(13.5, m.at(3, 0), delta);
        assertEquals(15.5, m.at(3, 2), delta);
    }
}
