package com.ulysses.raytracer;

public class Ray {
    Tuple origin, direction;

    public Ray(Tuple newOrigin, Tuple newDirection) { this.origin = newOrigin; this.direction = newDirection; }

    public Tuple Position(double t) {
        return Tuple.multiply(Tuple.add(origin, direction), t);
    }

}
