package com.charlezz.dagger2;

import org.junit.Test;

import com.charlezz.dagger2.dagger.test.cafe.Cafe;

public class CoffeeUnitTest {
    @Test
    public void testCafe() {
        Cafe cafe = new Cafe();
        System.out.println(cafe.orderCoffee());
        System.out.println(cafe.orderCoffee());
        System.out.println(cafe.orderCoffee());
    }
}
