package com.charlezz.dagger2.dagger.test.case06;

import dagger.Module;
import dagger.Provides;

@Module
public class HelloModule {
    @Provides
    String provideString() {
        return "Hello";
    }
}