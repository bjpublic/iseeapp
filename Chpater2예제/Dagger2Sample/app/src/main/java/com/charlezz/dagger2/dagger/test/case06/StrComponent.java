package com.charlezz.dagger2.dagger.test.case06;

import dagger.Component;

@Component(modules = {CommonModule.class, HelloModule.class})
public interface StrComponent {
    void inject(Foo foo);

}