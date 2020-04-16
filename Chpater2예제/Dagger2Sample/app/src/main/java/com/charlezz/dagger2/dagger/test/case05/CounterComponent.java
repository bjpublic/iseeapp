package com.charlezz.dagger2.dagger.test.case05;

import dagger.Component;

@Component(modules = CounterModule.class)
public interface CounterComponent {
    void inject(Counter counter);
}