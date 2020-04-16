package com.charlezz.dagger2.dagger.test.case03;

import dagger.Component;

@Component(modules = MyModule.class)
public interface MyComponent2 {
    Person getPerson();
}
