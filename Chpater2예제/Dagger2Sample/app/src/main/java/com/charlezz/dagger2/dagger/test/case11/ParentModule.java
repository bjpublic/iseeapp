package com.charlezz.dagger2.dagger.test.case11;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module(subcomponents = ChildComponent.class)
public class ParentModule {
    @Provides
    @IntoSet
    String string1() {
        return "parent string 1";
    }

    @Provides
    @IntoSet
    String string2() {
        return "parent string 2";
    }

}