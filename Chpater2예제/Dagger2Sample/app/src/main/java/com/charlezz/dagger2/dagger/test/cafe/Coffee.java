package com.charlezz.dagger2.dagger.test.cafe;

import javax.inject.Inject;

public class Coffee {
    private final CoffeeBean coffeeBean;
    private final Water water;

    @Inject
    public Coffee(CoffeeBean coffeeBean, Water water) {
        this.coffeeBean = coffeeBean;
        this.water = water;
    }
}