package com.charlezz.dagger2.dagger.test.case13;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleA {
    @Provides
    String provideString() {
        return "String from ModuleA";
    }
}