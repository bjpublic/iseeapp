package com.charlezz.dagger2.dagger.test.case06;

import dagger.Component;

@Component(modules = CommonModule.class)
public interface NoStrComponent {
    void inject(Foo foo);
}