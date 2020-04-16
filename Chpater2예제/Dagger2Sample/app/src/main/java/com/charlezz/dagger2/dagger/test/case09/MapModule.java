package com.charlezz.dagger2.dagger.test.case09;

import com.charlezz.dagger2.dagger.test.case06.Foo;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public class MapModule {

    @Provides
    @IntoMap
    @StringKey("foo")
    static Long provideFooValue() {
        return 100L;
    }

    @Provides
    @IntoMap
    @ClassKey(Foo.class)
    static String provideFooStr() {
        return "Foo String";
    }

}