package com.charlezz.dagger2.dagger.test.case04;

import javax.inject.Inject;

public class PersonA {

    private String name;
    private int age;

    @Inject //생성자 주입
    public PersonA(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}