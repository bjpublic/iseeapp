package com.charlezz.dagger2.dagger.test.case01;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Hello {
}
