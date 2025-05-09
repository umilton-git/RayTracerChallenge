package com.ulysses.raytracer;

import org.junit.Test;
import static org.junit.Assert.*;

public class TupleTest {

    double delta = 1e-9;
    @Test
    public void testTuplePoint(){
        Tuple a = new Tuple(4.3, -4.2, 3.1, 1.0);
        assertEquals(4.3, a.x, delta);
        assertEquals(-4.2, a.y, delta);
        assertEquals(3.1, a.z, delta);
        assertEquals(1.0, a.w, delta);
    }

    @Test
    public void testTupleVector(){
        Tuple a = new Tuple(4.3, -4.2, 3.1, 0.0);
        assertEquals(4.3, a.x, delta);
        assertEquals(-4.2, a.y, delta);
        assertEquals(3.1, a.z, delta);
        assertEquals(0.0, a.w, delta);
    }

    @Test
    public void testPoint(){
        Tuple p = Tuple.point(4, -4, 3);
        assertEquals(4.0, p.x, delta);
        assertEquals(-4.0, p.y, delta);
        assertEquals(3.0, p.z, delta);
        assertEquals(1.0, p.w, delta);
    }

    @Test
    public void testVector(){
        Tuple p = Tuple.vector(4, -4, 3);
        assertEquals(4.0, p.x, delta);
        assertEquals(-4.0, p.y, delta);
        assertEquals(3.0, p.z, delta);
        assertEquals(0.0, p.w, delta);
    }

    @Test
    public void testAdd(){
        Tuple a1 = Tuple.point(3,-2,5);
        Tuple a2 = Tuple.vector(-2,3,1);
        Tuple actual = Tuple.add(a1, a2);
        assertEquals(1.0, actual.x, delta);
        assertEquals(1.0, actual.y, delta);
        assertEquals(6.0, actual.z, delta);
        assertEquals(1.0, actual.w, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPointAddition() {
        Tuple p1 = Tuple.point(1, 2, 3);
        Tuple p2 = Tuple.point(4, 5, 6);
        Tuple.add(p1, p2);
    }

    @Test
    public void testSub(){
        // Subtracting two points
        Tuple p1 = Tuple.point(3, 2, 1);
        Tuple p2 = Tuple.point(5, 6, 7);
        Tuple actual = Tuple.sub(p1, p2);
        assertEquals(-2.0, actual.x, delta);
        assertEquals(-4.0, actual.y, delta);
        assertEquals(-6.0, actual.z, delta);
        assertEquals(1.0, actual.w, delta);

        // Subtracting two vectors
        p1 = Tuple.vector(3, 2, 1);
        p2 = Tuple.vector(5, 6, 7);
        actual = Tuple.sub(p1, p2);
        assertEquals(-2.0, actual.x, delta);
        assertEquals(-4.0, actual.y, delta);
        assertEquals(-6.0, actual.z, delta);
        assertEquals(0.0, actual.w, delta);

        // Subtracting a vector from a point
        p1 = Tuple.point(3, 2, 1);
        p2 = Tuple.vector(5, 6, 7);
        actual = Tuple.sub(p1, p2);
        assertEquals(-2.0, actual.x, delta);
        assertEquals(-4.0, actual.y, delta);
        assertEquals(-6.0, actual.z, delta);
        assertEquals(1.0, actual.w, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPointFromVecSub(){
        Tuple p1 = Tuple.vector(1, 2, 3);
        Tuple p2 = Tuple.point(4, 5, 6);
        Tuple.sub(p1, p2);
    }

    @Test
    public void testNegate(){
        Tuple a = new Tuple(1, 2, 3, -4);
        Tuple b = Tuple.negate(a);
        assertEquals(-1.0, b.x, delta);
        assertEquals(-2.0, b.y, delta);
        assertEquals(-3.0, b.z, delta);
        assertEquals(4.0, b.w, delta);
    }

    @Test
    public void testMultiply(){
        // Multiplying by a scalar
        Tuple a = new Tuple(1, -2, 3, -4);
        Tuple b = Tuple.multiply(a, 3.5);
        assertEquals(3.5, b.x, delta);
        assertEquals(-7, b.y, delta);
        assertEquals(10.5, b.z, delta);
        assertEquals(-14, b.w, delta);

         // Multiplying by a fraction
        b = Tuple.multiply(a, 0.5);
        assertEquals(0.5, b.x, delta);
        assertEquals(-1, b.y, delta);
        assertEquals(1.5, b.z, delta);
        assertEquals(-2, b.w, delta);
    }

    @Test
    public void testDivide(){
        // Dividing by a scalar
        Tuple a = new Tuple(1, -2, 3, -4);
        Tuple b = Tuple.divide(a, 2);
        assertEquals(0.5, b.x, delta);
        assertEquals(-1, b.y, delta);
        assertEquals(1.5, b.z, delta);
        assertEquals(-2, b.w, delta);
    }

    @Test
    public void testMagnitude(){
        Tuple a = Tuple.vector(1, 0, 0);
        double actual = a.magnitude();
        assertEquals(1.0, actual, delta);

        a = Tuple.vector(0, 1, 0);
        actual = a.magnitude();
        assertEquals(1.0, actual, delta);

        a = Tuple.vector(0, 0, 1);
        actual = a.magnitude();
        assertEquals(1.0, actual, delta);

        a = Tuple.vector(1, 2, 3);
        actual = a.magnitude();
        assertEquals(Math.sqrt(14), actual, delta);

        a = Tuple.vector(-1, -2, -3);
        actual = a.magnitude();
        assertEquals(Math.sqrt(14), actual, delta);
    }

    @Test
    public void testNormalize(){
        Tuple a = Tuple.vector(4, 0, 0);
        Tuple b = Tuple.normalize(a);
        assertEquals(1.0, b.x, delta);
        assertEquals(0.0, b.y, delta);
        assertEquals(0.0, b.z, delta);
        assertEquals(0.0, b.w, delta);
    }

    @Test
    public void testDotProduct(){
        Tuple a = Tuple.vector(1, 2, 3);
        Tuple b = Tuple.vector(2, 3, 4);
        double actual = Tuple.dotProduct(a, b);
        assertEquals(20.0, actual, delta);
    }

    @Test
    public void testCrossProduct(){
        Tuple a = Tuple.vector(1, 2, 3);
        Tuple b = Tuple.vector(2, 3, 4);
        Tuple actualA = Tuple.crossProduct(a, b);
        Tuple actualB = Tuple.crossProduct(b, a);

        assertEquals(-1.0, actualA.x, delta);
        assertEquals(2.0, actualA.y, delta);
        assertEquals(-1.0, actualA.z, delta);
        assertEquals(1.0, actualB.x, delta);
        assertEquals(-2.0, actualB.y, delta);
        assertEquals(1.0, actualB.z, delta);
    }
}
