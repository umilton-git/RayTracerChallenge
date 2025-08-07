package com.ulysses.raytracer;

import org.junit.Test;
import static org.junit.Assert.*;

public class RayTest {

    double delta = 1e-9;

    @Test
    public void TestRay()
    {
        Ray r = new Ray(Tuple.point(2,3,4), Tuple.vector(1,0,0));
        Tuple point = Tuple.point (2,3,4);
        Tuple vector = Tuple.vector(1,0,0);

        assertEquals(point.x,  r.origin.x, delta);
        assertEquals(point.y,  r.origin.y, delta);
        assertEquals(point.z,  r.origin.z, delta);
        assertEquals(point.w,  r.origin.w, delta);

        assertEquals(vector.x,  r.direction.x, delta);
        assertEquals(vector.y,  r.direction.y, delta);
        assertEquals(vector.z,  r.direction.z, delta);
        assertEquals(vector.w,  r.direction.w, delta);
    }

    @Test
    public void TestPosition()
    {
        Ray r = new Ray(Tuple.point(2,3,4), Tuple.vector(1,0,0));

        Tuple testPoint1 = r.Position(0);
        Tuple testPoint2 = r.Position(1);
        Tuple testPoint3 = r.Position(-1);
        Tuple testPoint4 = r.Position(2.5);

    }
}
