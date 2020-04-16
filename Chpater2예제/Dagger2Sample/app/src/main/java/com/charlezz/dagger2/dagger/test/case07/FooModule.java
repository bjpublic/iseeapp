package com.charlezz.dagger2.dagger.test.case07;

import dagger.Module;
import dagger.Provides;

@Module
public class FooModule {
    @Provides
    String provideHello() {
        return "Hello";
    }
}
