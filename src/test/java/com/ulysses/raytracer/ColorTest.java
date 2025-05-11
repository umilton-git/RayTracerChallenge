package com.ulysses.raytracer;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColorTest {

    double delta = 1e-9;

    @Test
    public void testColor(){
        Color c = new Color(-0.5, 0.4, 1.7);
        assertEquals(-0.5, c.r(), delta);
        assertEquals(0.4, c.g(), delta);
        assertEquals(1.7, c.b(), delta);
    }

    @Test
    public void testAddingColors(){
        Color c = new Color(0.9, 0.6, 0.75);
        Color d = new Color(0.7, 0.1, 0.25);
        Color e = c.add(d);

        assertEquals(1.6, e.r(), delta);
        assertEquals(0.7, e.g(), delta);
        assertEquals(1.0, e.b(), delta);
    }

    @Test
    public void testSubtractingColors(){
        Color c = new Color(0.9, 0.6, 0.75);
        Color d = new Color(0.7, 0.1, 0.25);
        Color e = c.subtract(d);

        assertEquals(0.2, e.r(), delta);
        assertEquals(0.5, e.g(), delta);
        assertEquals(0.5, e.b(), delta);
    }

    @Test
    public void testMultColors(){
        Color c1 = new Color(1, 0.2, 0.4);
        Color c2 = new Color(0.9, 1, 0.1);

        Color c3 = c1.hadamard(c2);

        assertEquals(0.9, c3.r(), delta);
        assertEquals(0.2, c3.g(), delta);
        assertEquals(0.04, c3.b(), delta);
    }
}
