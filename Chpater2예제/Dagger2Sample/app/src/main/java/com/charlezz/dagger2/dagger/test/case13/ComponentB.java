package com.charlezz.dagger2.dagger.test.case13;

import dagger.Component;

@Component(modules = ModuleB.class, dependencies = ComponentA.class)
public interface ComponentB {
    void inject(Foo4 foo);
}