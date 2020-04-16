package com.charlezz.dagger2.dagger.test.case04;

import dagger.Component;

@Component(modules = PersonModule.class)

public interface PersonComponent {
    PersonA getPersonA();// 프로비전 메서드
    void inject(PersonB personB);// 멤버-인젝션 메서드

}