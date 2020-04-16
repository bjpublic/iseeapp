package com.charlezz.dynamicproxy;

public class Multiplier2x implements Multiplier {
    @Override
    public int multiply(int value) {
        return value * 2;
    }
}
