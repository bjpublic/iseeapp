package com.charlezz.dagger2.dagger.test.case01;

import javax.inject.Singleton;

import androidx.annotation.Nullable;
import dagger.Component;

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {
    String getString(); //프로비전 메서드, 바인드된 모듈로부터 의존성을 제공

    @Nullable
    Integer getInteger();

    void inject(MyClass myClass);

    Object getObject();

}