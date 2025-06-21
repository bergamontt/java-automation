package com;

import lombok.Data;

@Data
public class Tuple {
    private double x;
    private double y;
    private double z;
    private double w;

    public final static int VECTOR = 0;
    public final static int POINT = 1;

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Tuple createPoint(double x, double y, double z) {
        return new Tuple(x, y, z, POINT);
    }

    public static Tuple createVector(double x, double y, double z) {
        return new Tuple(x, y, z, VECTOR);
    }

    public Tuple multiply(double scalar) {
        return new Tuple(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Tuple add(Tuple tuple) {
        return new Tuple(x + tuple.x, y + tuple.y, z + tuple.z, w + tuple.w);
    }

    public Tuple subtract(Tuple tuple) {
        return new Tuple(x - tuple.x, y - tuple.y, z - tuple.z, w - tuple.w);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public double dot(Tuple tuple) {
        return x * tuple.x + y * tuple.y + z * tuple.z + w * tuple.w;
    }

    public Tuple cross(Tuple tuple) {
        return Tuple.createVector(y * tuple.z - z * tuple.y,
                                  z * tuple.x - x * tuple.z,
                                  x * tuple.y - y * tuple.x);
    }

    public boolean isPoint() {
        return w == POINT;
    }

    public boolean isVector() {
        return w == VECTOR;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tuple tuple)) return false;
        return  Double.compare(x, tuple.x) == 0 &&
                Double.compare(y, tuple.y) == 0 &&
                Double.compare(z, tuple.z) == 0 &&
                Double.compare(w, tuple.w) == 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}