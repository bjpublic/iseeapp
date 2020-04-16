package com.charlezz.dagger2.dagger.test.case01;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.annotation.Nullable;
import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {
    @Provides
    String provideHelloWorld() {
        return "Hello World";

    }

    @Provides
    @Nullable
    Integer provideInteger() { //null을 반환할 가능성이 있는 경우
        return null;
    }

    @Provides
    @Named("hello")
    String provideHello() {
        return "Hello";
    }


    @Provides
    @Named("world")
    String provideWorld() {
        return "World";
    }

    @Provides
    @Hello
    String provideHelloWithCustomQualifier(){
        return "Hello";
    }

    @Provides
    @Singleton
    Object provideObject(){
        return new Object();
    }

}