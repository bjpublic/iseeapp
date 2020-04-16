package com.charlezz.dagger2.dagger.test.case10;

import dagger.MapKey;

@MapKey
public @interface NumberKey {
    Class<? extends Number> value();
}