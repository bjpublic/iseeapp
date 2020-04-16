package com.charlezz.dagger2.dagger.test.case02;

import dagger.Module;
import dagger.Provides;

@Module
class DuplicationModule {
    @Provides
    String provideHelloWorld() {
        return "Hello World";
    }

    @Provides
    String provideCharles() {
        return "Charles"; //동일한 타입이 2개 이상 존재하므로 에러
    }
}