package com.charlezz.dagger2.dagger.test.case03;

import dagger.Module;
import dagger.Provides;

@Module

public class MyModule {

    @Provides
    String provideName() { //이름 제공
         return "Charles";
    }

    @Provides
    int provideAge() { //나이 제공
        return 100;
    }

    @Provides
    Person providePerson(String name, int age) { //이름, 나이 제공받음
        return new Person(name, age);//name = Charles, age = 100
    }

}