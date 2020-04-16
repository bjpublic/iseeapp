package com.charlezz.dagger2.dagger.test.case08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;

@Module
public class SetModule {
    @Provides
    @IntoSet
    String provideHello() {
        return "Hello";
    }

    @Provides
    @IntoSet
    String provideWorld() {
        return "World";
    }

    @Provides
    @ElementsIntoSet
    Set<String> provideSet() {
        return new HashSet<>(Arrays.asList("Charles", "Runa"));

    }

}