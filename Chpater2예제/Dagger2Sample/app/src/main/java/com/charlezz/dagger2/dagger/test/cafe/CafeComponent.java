package com.charlezz.dagger2.dagger.test.cafe;

import dagger.Component;

@Component(modules = CafeModule.class)
public interface CafeComponent {
    void inject(Cafe cafe);

}