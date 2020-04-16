package com.charlezz.dagger2.dagger.test.case05;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class Counter {

    @Inject
    Lazy<Integer> lazy;

    @Inject
    Provider<Integer> provider;

    public void printLazy() {
        System.out.println("printing...");
        System.out.println(lazy.get());
        System.out.println(lazy.get());
        System.out.println(lazy.get());
    }

    public void printProvider(){
        System.out.println("printing...");
        System.out.println(provider.get());
        System.out.println(provider.get());
        System.out.println(provider.get());
    }

}