package com.charlezz.dagger2.dagger.test.case04;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {

    @Provides
    String provideName() {
        return "Charles";
    }

    @Provides
    int provideAge() {
        return 100;
    }

}