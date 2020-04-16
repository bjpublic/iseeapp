package com.charlezz.dagger2.dagger.test.case02;

import dagger.Component;

@Component(modules = DuplicationModule.class)
public interface DuplicationComponent {
//   String getString();
//   주석을 풀면 에러발생:java.lang.String is bound multiple times

}