package com.ulysses.raytracer;

public class Tuple {
    double x, y, z, w;

    public Tuple(double newX, double newY, double newZ, double newW) {
        x = newX; y = newY; z = newZ; w = newW;
    }

    public static Tuple point(double newX, double newY, double newZ){
        return new Tuple(newX, newY, newZ, 1.0);
    }

    public static Tuple vector(double newX, double newY, double newZ){
        return new Tuple(newX, newY, newZ, 0.0);
    }

    public static Tuple add(Tuple a, Tuple b){
        Tuple newTuple = a;
        if (a.w == 1.0 && b.w == 1.0) {
            throw new IllegalArgumentException("Error: Cannot add two points; result would not be a valid Tuple.");
        }
        else {
            newTuple = new Tuple(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
        }
        return newTuple;
    }

    public static Tuple sub(Tuple a, Tuple b){
        Tuple newTuple = a;
        if(a.w == 0 && b.w == 1){
            throw new IllegalArgumentException("Error: Cannot subtract a point from a vector; result would be a valid Tuple.");
        } else if (a.w == 0 && b.w == 0){
            newTuple = Tuple.vector(a.x - b.x, a.y - b.y, a.z - b.z);
        }
        else{
            newTuple = Tuple.point(a.x - b.x, a.y - b.y, a.z -b.z);
        }
        return newTuple;
    }

    public static Tuple negate(Tuple a){
        return new Tuple(-a.x, -a.y, -a.z, -a.w);
    }

    public static Tuple multiply(Tuple a, double num){
        return new Tuple(a.x * num, a.y * num, a.z * num, a.w * num);
    }

    public static Tuple divide(Tuple a,double num){
        return new Tuple(a.x / num, a.y / num, a.z / num, a.w / num);
    }

    public double magnitude(){
        return(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) + Math.pow(w, 2)));
    }

    public static Tuple normalize(Tuple a){
        return new Tuple(a.x/a.magnitude(), a.y/a.magnitude(), a.z/a.magnitude(), a.w/a.magnitude());
    }

    public static double dotProduct(Tuple a, Tuple b){
        return(a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w);
    }

    public static Tuple crossProduct(Tuple a, Tuple b){
        return(Tuple.vector(a.y * b.z - a.z * b.y,
                            a.z * b.x - a.x * b.z,
                            a.x * b.y - a.y * b.x));
    }
}

