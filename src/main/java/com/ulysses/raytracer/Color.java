package com.ulysses.raytracer;

public record Color(double r, double g, double b) {
    public Color add(Color c)         { return new Color(r + c.r, g + c.g, b + c.b); }
    public Color subtract(Color c)    { return new Color(r - c.r, g - c.g, b - c.b); }
    public Color scale(double k)      { return new Color(r * k, g * k, b * k); }
    public Color hadamard(Color c)    { return new Color(r * c.r, g * c.g, b * c.b); }

    /** Clamp each component to [0, 1]. */
    public Color clamp() {
        return new Color(
                Math.max(0, Math.min(1, r)),
                Math.max(0, Math.min(1, g)),
                Math.max(0, Math.min(1, b))
        );
    }
}

