package com.charlezz.dagger2.dagger.test.case07;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface BindsComponent {
    void inject(Foo2 foo2);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder setString(String str);
        BindsComponent build();
    }

}