package com.charlezz.dagger2.dagger.test.case11;

import java.util.Set;

import dagger.Subcomponent;

@Subcomponent(modules = ChildModule.class)
public interface ChildComponent {

    Set<String> strings();

    @Subcomponent.Builder
    interface Builder {
        Builder setChildModule(ChildModule childModule);
        ChildComponent build();
    }

}