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

    @Test
    public void testEquals(){
        Matrix matA = new Matrix(4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2);
        Matrix matB = new Matrix(4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2);
        Matrix matC = new Matrix(4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 4);

        assertTrue(matB.equals(matA));
        assertFalse(matA.equals(matC));
    }

    @Test
    public void testMultiply(){
        Matrix matA = new Matrix(4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2);
        Matrix matB = new Matrix(4, 4, -2, 1, 2, 3, 3, 2, 1, -1, 4, 3, 6, 5, 1, 2, 7, 8);
        Matrix expected = new Matrix(4, 4, 20, 22, 50, 48, 44, 54, 114, 108, 40, 58, 110, 102, 16, 26, 46, 42);
        Matrix result = matA.matMultiply(matB);

        assertTrue(expected.equals(result));
    }

    @Test
    public void testMultTuple(){
        Matrix mat = new Matrix(4, 4, 1, 2, 3, 4, 2, 4, 4, 2, 8, 6, 4, 1, 0, 0, 0, 1);
        Tuple t = new Tuple(1, 2, 3, 1);
        Tuple result = mat.matMultTuple(t);

        assertEquals(18, result.x,  delta);
        assertEquals(24, result.y,  delta);
        assertEquals(33, result.z,  delta);
        assertEquals(1, result.w,  delta);
    }

    @Test
    public void testTranspose(){
        Matrix m = new Matrix(4, 4, 0,9,3,0,9,8,0,8,1,8,5,3,0,0,5,8);
        Matrix expected = new Matrix(4,4, 0,9,1,0,9,8,8,0,3,0,5,5,0,8,3,8);
        Matrix result = Matrix.matTranspose(m);
        assertTrue(expected.equals(result));
    }
}
