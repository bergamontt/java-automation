package com;

public class Main {

    public static void main(String[] args) {
        Tuple tuple = Tuple.createVector(3, 2, 1);
        Tuple tuple2 = Tuple.createVector(5, 6, 7);
        tuple.subtract(tuple2);
    }

}