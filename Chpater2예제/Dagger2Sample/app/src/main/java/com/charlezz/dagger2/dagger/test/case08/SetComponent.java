package com.charlezz.dagger2.dagger.test.case08;

import dagger.Component;

@Component(modules = SetModule.class)
public interface SetComponent {
    void inject(Foo3 foo);

}